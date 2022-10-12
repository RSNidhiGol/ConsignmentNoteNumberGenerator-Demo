package com.freightmate.consignment.service;

import com.freightmate.consignment.ConsignmentGeneratorApplication;
import com.freightmate.consignment.model.CarrierAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ConsignmentGeneratorApplication.class)
public class ConsignmentGeneratorServiceTest {

    @Autowired
    ConsignmentGeneratorService service;

    @Test
    void generatePrefix() {
        String result = service.generatePrefix("FreightMateCourierCo");
        assertEquals("FMCC", result);
    }

    @Test
    void calculateConsignmentIndex(){
        CarrierAccount carrierAccount = new CarrierAccount();
        carrierAccount.setDigits(10);
        carrierAccount.setLastUsedIndex(19604);
        carrierAccount.setRangeStart(19000);
        carrierAccount.setRangeEnd(20000);

        String consignmentIndex = service.calculateConsignmentIndex(carrierAccount);
        assertEquals("0000019605", consignmentIndex);
    }

    @Test
    void calculateChecksum() {
        int checkSum = service.calculateChecksum("0000019605");
        assertEquals(1, checkSum);
    }
}
