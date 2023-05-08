package com.example.infrastructures.service;

import com.example.infrastructures.domain.Customer;
import com.example.infrastructures.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
public class CustomerService {

    private final CustomersRepository customersRepository;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CustomerService(CustomersRepository customersRepository, JdbcTemplate jdbcTemplate) {
        this.customersRepository = customersRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addCustomerByJpa() {
        Customer customer = new Customer("Poziomkowa 20, 00-400 Warszawa", "Podwale 23, 58-300 Wrocław", "economic", "big", LocalDate.of(2020, 5, 10));
        customersRepository.save(customer);
        getAllCustomersByJdbc();
    }

    public List<Map<String, Object>> getAllCustomersByJdbc() {

        String sql = "SELECT * FROM Customers";
        System.out.println(jdbcTemplate.queryForList(sql, new Object[]{}));
        return jdbcTemplate.queryForList(sql, new Object[]{});
    }
}
