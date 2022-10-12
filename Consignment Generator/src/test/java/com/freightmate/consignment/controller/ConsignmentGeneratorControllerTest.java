package com.freightmate.consignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.freightmate.consignment.ConsignmentGeneratorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = ConsignmentGeneratorApplication.class)
@AutoConfigureMockMvc
@EnableWebMvc
class ConsignmentGeneratorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void generateConsignmentNumber_success() throws Exception {

        String input = "{\n" +
                "  \"carrierName\":\"FreightMateCourierCo\",\n" +
                "  \"accountNumber\":\"123ABC\",\n" +
                "  \"digits\":10,\n" +
                "  \"lastUsedIndex\":19604,\n" +
                "  \"rangeStart\":19000,\n" +
                "  \"rangeEnd\":20000\n" +
                "}";

        this.mockMvc.perform(post("/api/consignment/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andExpect(status().isOk())
                .andExpect(content().string("FMCC123ABC00000196051")
        );
    }

    @Test
    void generateConsignmentNumber_rangeNotValid() throws Exception {

        String input = "{\n" +
                "  \"carrierName\":\"FreightMateCourierCo\",\n" +
                "  \"accountNumber\":\"123ABC\",\n" +
                "  \"digits\":10,\n" +
                "  \"lastUsedIndex\":18604,\n" +
                "  \"rangeStart\":19000,\n" +
                "  \"rangeEnd\":20000\n" +
                "}";

        this.mockMvc.perform(post("/api/consignment/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input))
                .andExpect(status().isBadRequest());
    }
}
