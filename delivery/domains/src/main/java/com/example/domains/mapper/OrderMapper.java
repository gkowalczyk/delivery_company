package com.example.domains.mapper;

import com.delivery_company.openapi.model.OrderDto;
import com.example.domains.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface OrderMapper {
    Order orderToEntity(OrderDto orderDto);

    OrderDto orderToDto(Order order);
}
