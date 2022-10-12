package com.freightmate.consignment.controller;

import com.freightmate.consignment.model.CarrierAccount;
import com.freightmate.consignment.service.ConsignmentGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * The type Consignment generator controller.
 */
@RestController
@RequestMapping("api/consignment")
public class ConsignmentGeneratorController {

    @Autowired
    private ConsignmentGeneratorService service;

    /**
     * Generate consignment number string.
     *
     * @param carrierAccount the carrier account
     * @return the string
     */
    @PostMapping(path = "/generate")
    public String generateConsignmentNumber(@Valid @RequestBody CarrierAccount carrierAccount){

        return service.generateConsignmentNoteNumber(carrierAccount);
    }
}
