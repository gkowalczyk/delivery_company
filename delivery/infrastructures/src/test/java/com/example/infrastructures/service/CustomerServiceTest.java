package com.example.infrastructures.service;


import com.example.infrastructures.domain.Customer;
import com.example.infrastructures.repository.CustomersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomersRepository customersRepository;
}


   /* @Test
    void getAllCustomersFromDataBase() {

        //given
        customersRepository.save(new Customer("Poziomkowa 20, 00-400 Warszawa", "Podwale 23, 58-300 Wrocław", "economic", "big", LocalDate.of(2020, 5, 10)));
        //when
        List<Customer> customerList = customersRepository.findAll();
        //then
        assertEquals(customerList.size(), 2);
        customersRepository.findAll().forEach(System.out::println);
        //clean
        customersRepository.deleteAll();
    }
}
*/