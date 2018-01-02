package com.example.demo.commons.errors;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ErrorResponseTest {

    @Test
    public void test_Create_Response() {
        // Given
        ErrorResponse er = new ErrorResponse();
        ErrorResponse er1 = new ErrorResponse();

        // When
        er.setCode("123");
        er.setMessage("456");

        // Then
        assertTrue(er.getCode().equals("123"));
        assertTrue(er.getMessage().equals("456"));
        assertTrue(er1.getCode().equals("???"));
        assertTrue(er1.getMessage().equals("No specific message"));
    }

}