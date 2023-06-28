package com.example.domains.controller;


import com.delivery_company.openapi.api.ApiApi;
import com.delivery_company.openapi.model.CustomerDto;
import com.example.domains.domain.Customer;
import com.example.domains.mapper.CustomerMapper;
import com.example.domains.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")

public class CustomerController implements ApiApi {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }


    /**
     * GET /api/customers : Get a list of all customers
     *
     * @return OK (status code 200)
     */
    @Override
       public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers()
                .stream().map(customerMapper::customerToDto)
                .collect(Collectors.toList()));
    }

    /**
     * GET /api/customers/{id} : Get details of a specific customer
     *
     * @param id (required)
     * @return OK (status code 200)
     */

    @Override
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(customerMapper.customerToDto(customerService.getCustomer(id)));
    }

    /**
     * POST /api/customers : Add a new customer
     *
     * @param customerDto (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody CustomerDto customerDto) {
        Customer newCustomer = customerMapper.customerToEntity(customerDto);
        customerService.addCustomer(newCustomer);
        return ResponseEntity.ok().build();
    }

    /**
     * PUT /api/customers/{id} : Update an existing customer
     *
     * @param id          (required)
     * @param customerDto (required)
     * @param id          (required)
     * @return OK (status code 200)
     * <p>
     * DELETE /api/customers/{id} : Delete a customer
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id") Integer id, @RequestBody CustomerDto customerDto) {

        Customer oldCustomer = customerMapper.customerToEntity(customerDto);
        Customer saveCustomer = customerService.updateCustomer(id, oldCustomer);
        return ResponseEntity.ok(customerMapper.customerToDto(saveCustomer));
    }

    /**
     * DELETE /api/customers/{id} : Delete a customer
     *
     * @param id (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok().build();
    }
}

