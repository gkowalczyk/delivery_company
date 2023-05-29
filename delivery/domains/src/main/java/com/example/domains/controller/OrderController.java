package com.example.domains.controller;

import com.delivery_company.openapi.api.ApiApi;
import com.delivery_company.openapi.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/order/")
public class OrderController implements ApiApi {

    /**
     * GET /api/orders/{orderId} : Retrieve order details by order ID
     *
     * @param orderId ID of the order to retrieve (required)
     * @return OK (status code 200)
     */

    @Override
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") Integer orderId) {

        //example implementation
        Order order = new Order();
        order.setOrderId(orderId);
        order.setCustomerId(1);
        order.setProducts(List.of("Product1", "Product2"));
        return ResponseEntity.ok(order);
    }

    /**
     * DELETE /api/orders/{orderId} : Cancel an order by order ID
     *
     * @param orderId ID of the order to cancel (required)
     * @return No Content (status code 204)
     */
    @Override
    public ResponseEntity<Void> cancelOrderById(@PathVariable("orderId") Integer orderId) {
        //example implementation
        List<Order> orderList = new ArrayList<>();
        Order order = new Order();
        order.setOrderId(1);
        orderList.add(order);
        orderList.removeIf(x -> x.getOrderId() == orderId);
        return ResponseEntity.ok().build();
    }


    /**
     * POST /api/orders : Place a new order
     *
     * @param order (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {

        //example implementation
        return ResponseEntity.ok(order);
    }

    /**
     * GET /api/customers/{customerId}/orders : Retrieve order history for a specific user
     *
     * @param customerId ID of the customer to retrieve order history for (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<List<Order>> getOrderHistoryByCustomerId(@PathVariable("customerId") Integer customerId) {

        //example implementation
        Order order = new Order();
        Order order1 = new Order();
        order.setOrderId(1);
        order.setCustomerId(1);
        order1.setOrderId(2);
        order1.setCustomerId(1);
        order.setProducts(List.of("Product1"));
        order1.setProducts(List.of("Product2"));
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        orderList.add(order1);
        return ResponseEntity.ok(orderList.stream().filter(x -> x.getCustomerId() == customerId).collect(Collectors.toList()));

    }
}