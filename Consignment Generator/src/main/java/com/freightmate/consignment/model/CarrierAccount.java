package com.freightmate.consignment.model;

import javax.validation.constraints.*;

public class CarrierAccount {

    @NotEmpty(message = "carrierName can not be empty")
    private String carrierName;

    @NotEmpty(message = "accountNumber can not be empty")
    private String accountNumber;

    @NotNull(message = "digits value is required")
    private Integer digits;

    @NotNull(message = "lastUsedIndex value is required")
    private Integer lastUsedIndex;

    @NotNull(message = "rangeStart value is required")
    private Integer rangeStart;

    @NotNull(message = "rangeEnd value is required")
    private Integer rangeEnd;

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getDigits() {
        return digits;
    }

    public void setDigits(Integer digits) {
        this.digits = digits;
    }

    public Integer getLastUsedIndex() {
        return lastUsedIndex;
    }

    public void setLastUsedIndex(Integer lastUsedIndex) {
        this.lastUsedIndex = lastUsedIndex;
    }

    public Integer getRangeStart() {
        return rangeStart;
    }

    public void setRangeStart(Integer rangeStart) {
        this.rangeStart = rangeStart;
    }

    public Integer getRangeEnd() {
        return rangeEnd;
    }

    public void setRangeEnd(Integer rangeEnd) {
        this.rangeEnd = rangeEnd;
    }
}
