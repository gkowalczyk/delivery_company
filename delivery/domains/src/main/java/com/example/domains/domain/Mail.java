package com.example.domains.domain;

import lombok.AllArgsConstructor;
import lombok.Data;



public class Mail {
    private String message;
    private String to;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Mail(String message, String to) {
        this.message = message;
        this.to = to;
    }
}
