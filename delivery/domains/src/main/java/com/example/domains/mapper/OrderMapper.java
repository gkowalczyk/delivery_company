package com.example.domains.mapper;

import com.delivery_company.openapi.model.OrderDto;
import com.example.domains.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TrackingInfoMapper.class})
public interface OrderMapper {
    @Mapping(source = "trackinginfoDto", target = "trackingInfo")
    Order orderToEntity(OrderDto orderDto);

    @Mapping(source = "trackingInfo", target = "trackinginfoDto")
    OrderDto orderToDto(Order order);
}
