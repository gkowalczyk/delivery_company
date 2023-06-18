package com.example.domains.mapper;

import com.delivery_company.openapi.model.CustomerDto;
import com.example.domains.domain.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CustomerMapper {
    Customer customerToEntity(com.delivery_company.openapi.model.CustomerDto customerDto);

    CustomerDto customerToDto(Customer customer);
}


