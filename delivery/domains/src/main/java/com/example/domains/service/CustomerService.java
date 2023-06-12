package com.example.domains.service;

import com.example.domains.domain.Customer;
import com.example.domains.repository.CustomersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomersRepository customersRepository;

    public CustomerService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public List<Customer> getAllCustomers() {
        return customersRepository.findAll();
    }

    public Customer getCustomer(Integer id) {
        return customersRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Customer addCustomer(final Customer customer) {
        return customersRepository.save(customer);
    }

    public Customer updateCustomer(Integer id, Customer customer) {
        Customer oldCustomer = customersRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        oldCustomer.setName(customer.getName());
        return customersRepository.save(oldCustomer);
    }

    public void deleteCustomerById(Integer id) {

        Optional<Customer> customer = Optional.ofNullable(customersRepository.findById(id).orElseThrow(EntityNotFoundException::new));
        Integer optionalId = customer.get().getId();
        customersRepository.deleteById(optionalId);
    }
}