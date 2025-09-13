package org.example.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalculationRequest {
    
    @JsonProperty("a")
    private int a;
    
    @JsonProperty("b")
    private int b;
    
    public CalculationRequest() {}
    
    public CalculationRequest(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    public int getA() { return a; }
    public void setA(int a) { this.a = a; }
    public int getB() { return b; }
    public void setB(int b) { this.b = b; }
    
    @Override
    public String toString() {
        return "CalculationRequest{a=" + a + ", b=" + b + '}';
    }
}