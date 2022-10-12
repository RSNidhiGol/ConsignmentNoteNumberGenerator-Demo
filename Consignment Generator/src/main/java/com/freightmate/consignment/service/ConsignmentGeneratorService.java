package com.freightmate.consignment.service;

import com.freightmate.consignment.exception.ConsignmentFieldValidation;
import com.freightmate.consignment.model.CarrierAccount;
import com.freightmate.consignment.model.SummationHolder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * The type Consignment generator service.
 */
@Service
public class ConsignmentGeneratorService {

    /**
     * Calculate checksum int.
     *
     * @param consignmentIndex the consignment index
     * @return the int
     */
    public int calculateChecksum(String consignmentIndex) {
        SummationHolder holder = getSummations(consignmentIndex);
        int results = performChecksumMultiplication(holder.getOddSum(), 3) + performChecksumMultiplication(holder.getEvenSum() ,7);
        return doDifferenceForChecksum(calculateNextMultipleOf10(performMod(results)),results);
    }

    /**
     * Gets summations of every first & second element.
     *
     * @param consignmentIndex the consignment index
     * @return the summations
     */
    private SummationHolder getSummations(String consignmentIndex)
    {
        int evenSum = 0;
        int oddSum = 0;
        for(int i=0 ; i<consignmentIndex.length(); i++){
            int num = Integer.parseInt(String.valueOf(consignmentIndex.charAt(i)));
            if(isEvenNumber(i)){
                evenSum = evenSum + num;
            } else {
                oddSum = oddSum + num;
            }
        }
        return new SummationHolder(evenSum,oddSum);
    }

    /**
     * Do difference for checksum int.
     *
     * @param tenMultiplier the ten multiplier
     * @param number        the number
     * @return the int
     */
    private int doDifferenceForChecksum(int tenMultiplier, int number)
    {
        return tenMultiplier - performMod(number);
    }

    /**
     * Perform mod int.
     *
     * @param number the number
     * @return the int
     */
    private int performMod(int number)
    {
        return (number < 0) ? number * -1 : number;
    }

    /**
     * Calculate next multiple of 10 int.
     *
     * @param number the number
     * @return the int
     */
    private int calculateNextMultipleOf10(int number)
    {
        return ((number / 10) + 1) * 10;
    }

    /**
     * Perform checksum multiplication int.
     *
     * @param number     the number
     * @param multiplier the multiplier
     * @return the int
     */
    private int performChecksumMultiplication(int number, int multiplier) {
        return number * multiplier;
    }

    /**
     * Is even number boolean.
     *
     * @param number the number
     * @return the boolean
     */
    private boolean isEvenNumber(int number)
    {
        return number % 2 == 0;
    }

    /**
     * Calculate consignment index string.
     *
     * @param carrierAccount the carrier account
     * @return the string
     */
    public String calculateConsignmentIndex(CarrierAccount carrierAccount) {

        checkDigitsValidation(carrierAccount);
        String index = String.format("%0"+ carrierAccount.getDigits()  +"d",
                carrierAccount.getLastUsedIndex()+1);

        checkRangeValidation(carrierAccount, Integer.parseInt(index));
        return index;
    }

    /**
     * Check digits validation.
     *
     * @param carrierAccount the carrier account
     */
    private void checkDigitsValidation(CarrierAccount carrierAccount) {
        if( ((int)Math.log10(carrierAccount.getLastUsedIndex()+1)) >= carrierAccount.getDigits()) {
            throw new ConsignmentFieldValidation("Digits can not be less than next consignment index.");
        }
    }

    /**
     * Check range validation.
     *
     * @param carrierAccount the carrier account
     * @param rangeIndex     the range index
     */
    private void checkRangeValidation(CarrierAccount carrierAccount, long rangeIndex) {
        if( !(rangeIndex >= carrierAccount.getRangeStart()
                && rangeIndex <= carrierAccount.getRangeEnd()) ){
            throw new ConsignmentFieldValidation("Consignment index must be within the provided range to be valid.");
        }
    }

    /**
     * Generate prefix string.
     *
     * @param carrierName the carrier name
     * @return the string
     */
    public String generatePrefix(String carrierName) {
        return carrierName
                .chars()
                .filter(Character::isUpperCase)
                .collect(StringBuilder::new, // supplier
                        StringBuilder::appendCodePoint, // accumulator
                        StringBuilder::append) // combiner
                .toString();
    }

    /**
     * Consignment note number summation string.
     *
     * @param prefix           the prefix
     * @param accountNumber    the account number
     * @param consignmentIndex the consignment index
     * @param checkSum         the check sum
     * @return the string
     */
    private String consignmentNoteNumberSummation(String prefix, String accountNumber, String consignmentIndex, int checkSum) {
        return prefix
                + accountNumber
                + consignmentIndex
                + checkSum;
    }

    /**
     * Generate consignment note number string.
     *
     * @param carrierAccount the carrier account
     * @return the string
     */
    public String generateConsignmentNoteNumber(CarrierAccount carrierAccount) {

        checkRangeStartEndValidation(carrierAccount);
        String consignmentIndex =  calculateConsignmentIndex(carrierAccount);

        int checkSum = calculateChecksum(consignmentIndex);
        String prefix = generatePrefix(carrierAccount.getCarrierName());
        if(prefix.isBlank()) {
            prefix = prefixValidation(prefix);
        }

        return consignmentNoteNumberSummation(
                prefix,
                carrierAccount.getAccountNumber(),
                consignmentIndex,
                checkSum);
    }

    /**
     * Check range start end validation.
     *
     * @param carrierAccount the carrier account
     */
    private void checkRangeStartEndValidation(CarrierAccount carrierAccount) {
        if(carrierAccount.getRangeStart() > carrierAccount.getRangeEnd()) {
            throw new ConsignmentFieldValidation("Range start value should not be higher than Range end value.");
        }
    }

    /**
     * Prefix validation string.
     *
     * @param prefix the prefix
     * @return the string
     */
    private String prefixValidation(String prefix) {

        return new Random().ints(97, 123)
                .limit(5)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
