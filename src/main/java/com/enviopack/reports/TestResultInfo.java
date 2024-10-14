package com.enviopack.reports;

public class TestResultInfo {
    private String name;
    private String status;
    private String message;

    // Constructor
    public TestResultInfo(String name, String status, String message) {
        this.name = name;
        this.status = status;
        this.message = message;
    }

    // Getters y setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
