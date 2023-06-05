package com.example.infrastructures.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
@Data
public abstract class BaseEntity {
    private String uuid = UUID.randomUUID().toString();
}

