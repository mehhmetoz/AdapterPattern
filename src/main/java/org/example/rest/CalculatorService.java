package org.example.rest;

import org.example.webservice.client.Calculator;
import org.example.webservice.client.CalculatorSoap;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public int add(int a, int b) {
        try {
            Calculator service = new Calculator();
            CalculatorSoap port = service.getCalculatorSoap();
            return port.add(a, b);
        } catch (Exception e) {
            throw new RuntimeException("SOAP service error during addition: " + e.getMessage(), e);
        }
    }

    public int subtract(int a, int b) {
        try {
            Calculator service = new Calculator();
            CalculatorSoap port = service.getCalculatorSoap();
            return port.subtract(a, b);
        } catch (Exception e) {
            throw new RuntimeException("SOAP service error during subtraction: " + e.getMessage(), e);
        }
    }

    public int multiply(int a, int b) {
        try {
            Calculator service = new Calculator();
            CalculatorSoap port = service.getCalculatorSoap();
            return port.multiply(a, b);
        } catch (Exception e) {
            throw new RuntimeException("SOAP service error during multiplication: " + e.getMessage(), e);
        }
    }

    public int divide(int a, int b) {
        try {
            Calculator service = new Calculator();
            CalculatorSoap port = service.getCalculatorSoap();
            return port.divide(a, b);
        } catch (Exception e) {
            throw new RuntimeException("SOAP service error during division: " + e.getMessage(), e);
        }
    }
}