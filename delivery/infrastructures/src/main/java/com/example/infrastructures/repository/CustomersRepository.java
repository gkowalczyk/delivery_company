package com.example.infrastructures.repository;

import com.example.infrastructures.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomersRepository extends JpaRepository<Customer,Long> {
}
