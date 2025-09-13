package org.example.soap;

import org.example.webservice.client.*;

public class SoapClient {

    public static void main(String[] args) {
        SoapClient client = new SoapClient();
        client.runCalculatorExample();
    }

    public void runCalculatorExample() {
        try {
            // Create service instance
            Calculator service = new Calculator();

            // Get port (proxy to the web service)
            CalculatorSoap port = service.getCalculatorSoap();

            // Call web service operations
            performCalculations(port);

        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void performCalculations(CalculatorSoap port) {
        System.out.println("=== Calculator Web Service Client ===");

        try {
            int a = 15, b = 5;

            int addResult = port.add(a, b);
            System.out.printf("Addition: %d + %d = %d%n", a, b, addResult);

            int subtractResult = port.subtract(a, b);
            System.out.printf("Subtraction: %d - %d = %d%n", a, b, subtractResult);

            int multiplyResult = port.multiply(a, b);
            System.out.printf("Multiplication: %d * %d = %d%n", a, b, multiplyResult);

            int divideResult = port.divide(a, b);
            System.out.printf("Division: %d / %d = %d%n", a, b, divideResult);

        } catch (Exception e) {
            System.err.println("Error during calculation: " + e.getMessage());
            throw e;
        }
    }
}