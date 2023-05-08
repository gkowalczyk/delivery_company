package com.example.infrastructures.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DbManagerTest {

    @Test
    void getConnection() {
        DbManager dbManager =  DbManager.getInstance();
        assertNotNull(dbManager.getConnection());
    }
}