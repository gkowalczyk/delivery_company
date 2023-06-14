package com.example.domains.controller;

import com.delivery_company.openapi.api.ApiApi;
import com.delivery_company.openapi.model.OrderDto;
import com.example.domains.domain.Order;
import com.example.domains.domain.TrackingInfo;
import com.example.domains.mapper.OrderMapper;
import com.example.domains.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/order/")
public class OrderController implements ApiApi {


    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    /**
     * GET /api/orders/{orderId} : Retrieve order details by order ID
     *
     * @param orderId ID of the order to retrieve (required)
     * @return OK (status code 200)
     */

    @Override
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(orderMapper.orderToDto(orderService.getOrder(orderId)));
    }

    /**
     * DELETE /api/orders/{orderId} : Cancel an order by order ID
     *
     * @param orderId ID of the order to cancel (required)
     * @return No Content (status code 204)
     */
    @Override
    public ResponseEntity<Void> cancelOrderById(@PathVariable("orderId") Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.ok().build();
    }

    /**
     * POST /api/orders : Place a new order
     *
     * @param orderDto (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderDto orderDto) {
        Order newOrder = orderMapper.orderToEntity(orderDto);
        orderService.addOrder(newOrder);
        return ResponseEntity.ok().build();
    }

    /**
     * GET /api/customers/{customerId}/orders : Retrieve order history for a specific user
     *
     * @param customerId ID of the customer to retrieve order history for (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<List<OrderDto>> getOrderHistoryByCustomerId(@PathVariable("customerId") Integer customerId) {
        return ResponseEntity.ok(orderService.getTrackingInfo(customerId)
                .stream().map(orderMapper::orderToDto)
                .collect(Collectors.toList()));
    }
}