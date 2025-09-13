package org.example.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> add(@RequestBody CalculationRequest request) {
        try {
            int result = calculatorService.add(request.getA(), request.getB());
            return ResponseEntity.ok(createSuccessResponse("add", request.getA(), request.getB(), result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/subtract")
    public ResponseEntity<Map<String, Object>> subtract(@RequestBody CalculationRequest request) {
        try {
            int result = calculatorService.subtract(request.getA(), request.getB());
            return ResponseEntity.ok(createSuccessResponse("subtract", request.getA(), request.getB(), result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/multiply")
    public ResponseEntity<Map<String, Object>> multiply(@RequestBody CalculationRequest request) {
        try {
            int result = calculatorService.multiply(request.getA(), request.getB());
            return ResponseEntity.ok(createSuccessResponse("multiply", request.getA(), request.getB(), result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/divide")
    public ResponseEntity<Map<String, Object>> divide(@RequestBody CalculationRequest request) {
        try {
            int result = calculatorService.divide(request.getA(), request.getB());
            return ResponseEntity.ok(createSuccessResponse("divide", request.getA(), request.getB(), result));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(createErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/operations")
    public ResponseEntity<Map<String, Object>> getAvailableOperations() {
        Map<String, Object> operations = new HashMap<>();
        operations.put("operations", new String[]{"add", "subtract", "multiply", "divide"});
        operations.put("description", "Calculator REST API - SOAP Service Wrapper");
        return ResponseEntity.ok(operations);
    }

    private Map<String, Object> createSuccessResponse(String operation, int a, int b, int result) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("operation", operation);
        response.put("operandA", a);
        response.put("operandB", b);
        response.put("result", result);
        response.put("expression", String.format("%d %s %d = %d", a, getOperationSymbol(operation), b, result));
        return response;
    }

    private Map<String, Object> createErrorResponse(String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("error", message);
        return response;
    }

    private String getOperationSymbol(String operation) {
        return switch (operation) {
            case "add" -> "+";
            case "subtract" -> "-";
            case "multiply" -> "*";
            case "divide" -> "/";
            default -> "?";
        };
    }
}