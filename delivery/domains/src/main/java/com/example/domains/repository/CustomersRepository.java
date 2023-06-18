package com.example.domains.repository;

import com.example.domains.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CustomersRepository extends JpaRepository<Customer,Integer> {
}
