package com.example.domains.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TrackingInfo {

    @Id
    @GeneratedValue
    private Integer id;
    private String location;
    private String status;

    public TrackingInfo() {
    }

    public TrackingInfo(String location, String status) {
        this.location = location;
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
