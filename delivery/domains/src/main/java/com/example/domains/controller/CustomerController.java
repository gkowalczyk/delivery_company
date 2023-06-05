package com.example.domains.controller;

import com.delivery_company.openapi.api.ApiApi;
import com.delivery_company.openapi.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/customer/")
public class CustomerController implements ApiApi {

    /**
     * GET /api/customers : Get a list of all customers
     *
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<List<Customer>> getAllCustomers() {

        //example implementation
        Customer customer = new Customer();
        Customer customer1 = new Customer();
        customer.setId(1);
        customer1.setId(2);
        customer.setName("Customer1");
        customer1.setName("Customer2");
        List<Customer> customers = List.of(customer1, customer);

        return ResponseEntity.ok(customers);
    }

    /**
     * GET /api/customers/{id} : Get details of a specific customer
     *
     * @param id (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Integer id) {

        //example implementation
        Customer customer = new Customer();
        customer.setId(id);
        customer.setName("Customer1");

        return ResponseEntity.ok(customer);
    }

    /**
     * POST /api/customers : Add a new customer
     *
     * @param customer (required)
     * @return OK (status code 200)
     */

    @Override
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        //example implementation
        return ResponseEntity.ok(customer);
    }

    /**
     * PUT /api/customers/{id} : Update an existing customer
     *
     * @param id       (required)
     * @param customer (required)
     * @return OK (status code 200)
     */

    @Override
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Integer id, @RequestBody Customer customer) {
        //example implementation
        Customer fromDB = new Customer();
        fromDB.setName("Customer");
        fromDB.setId(1);

        List<Customer> customerList = List.of(fromDB);
        Optional<Customer> customerOptional = Optional.of(customerList.stream().filter(c -> c.getId() == id).findAny().get());
        customerOptional.get().setName(customer.getName());
        customerOptional.get().setId(customer.getId());

        return ResponseEntity.ok(customerOptional.get());

    }


    /**
     * DELETE /api/customers/{id} : Delete a customer
     *
     * @param id (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") Integer id) {
        //example implementation
        List<Customer> customerList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Customer");
        customerList.add(customer);
        System.out.println(customerList);

        Iterator<Customer> customerIterator = customerList.stream()
                .filter(c -> c.getId().equals(id))
                .iterator();
        while (customerIterator.hasNext()) {
            Customer customers = customerIterator.next();
            customerList.remove(customers);
        }
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
