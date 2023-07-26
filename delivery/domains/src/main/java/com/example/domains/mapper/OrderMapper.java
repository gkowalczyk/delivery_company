package com.example.domains.mapper;

import com.delivery_company.openapi.model.OrderDto;
import com.example.domains.domain.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {TrackingInfoMapper.class})
public interface OrderMapper {
    @Mapping(source = "trackinginfoDto", target = "trackingInfo")
   @Mapping(source = "orderDate", target = "date", dateFormat = "yyyy.MM.dd")
    Order orderToEntity(OrderDto orderDto);

    @Mapping(source = "trackingInfo", target = "trackinginfoDto")
    @Mapping(source = "date", target = "orderDate", dateFormat = "yyyy.MM.dd")
    OrderDto orderToDto(Order order);
}
