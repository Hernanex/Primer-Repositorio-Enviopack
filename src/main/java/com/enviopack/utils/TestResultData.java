package com.enviopack.utils;

public class TestResultData {
    private String testName;
    private String status;
    private String message;

    // Constructor
    public TestResultData(String testName, String status, String message) {
        this.testName = testName;
        this.status = status;
        this.message = message;
    }

    // Getters
    public String getTestName() {
        return testName;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
