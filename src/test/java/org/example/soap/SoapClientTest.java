package org.example.soap;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import org.example.webservice.client.*;

public class SoapClientTest {

    private Calculator service;
    private CalculatorSoap port;

    @Before
    public void setUp() {
        service = new Calculator();
        port = service.getCalculatorSoap();
    }

    @After
    public void tearDown() {
        // Cleanup if necessary
        service = null;
        port = null;
    }

    @Test
    public void testServiceCreation() {
        assertNotNull("Service should not be null", service);
        assertNotNull("Port should not be null", port);
    }

    @Test
    public void testAddOperation() {
        try {
            int result = port.add(10, 5);
            assertEquals("Addition should return 15", 15, result);
        } catch (Exception e) {
            fail("Addition operation should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testSubtractOperation() {
        try {
            int result = port.subtract(10, 5);
            assertEquals("Subtraction should return 5", 5, result);
        } catch (Exception e) {
            fail("Subtraction operation should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testMultiplyOperation() {
        try {
            int result = port.multiply(10, 5);
            assertEquals("Multiplication should return 50", 50, result);
        } catch (Exception e) {
            fail("Multiplication operation should not throw exception: " + e.getMessage());
        }
    }

    @Test
    public void testDivideOperation() {
        try {
            int result = port.divide(10, 5);
            assertEquals("Division should return 2", 2, result);
        } catch (Exception e) {
            fail("Division operation should not throw exception: " + e.getMessage());
        }
    }
}
