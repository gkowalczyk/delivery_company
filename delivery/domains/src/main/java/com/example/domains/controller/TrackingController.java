package com.example.domains.controller;

import com.delivery_company.openapi.api.ApiApi;
import com.delivery_company.openapi.model.Order;
import com.delivery_company.openapi.model.TrackingInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/tracking/")
public class TrackingController implements ApiApi {

    /**
     * GET /api/orders/{orderId}/tracking : Retrieve real-time tracking information for an order
     *
     * @param orderId (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<Order> getTrackingInfo(@PathVariable("orderId") Integer orderId) {
        Order order = new Order();
        order.setOrderId(1);
        TrackingInfo trackingInfo = new TrackingInfo();
        trackingInfo.setStatus("in progress...");
        trackingInfo.setLocation("Wrocław");
        order.setExternalObjects(trackingInfo);
        return ResponseEntity.ok(order);

    }

    /**
     * PUT /api/orders/{orderId}/tracking : Update the status and location of an order during delivery
     *
     * @param orderId      (required)
     * @param trackingInfo (required)
     * @return OK (status code 200)
     */

    @Override
    public ResponseEntity<Order> updateTrackingInfo(@PathVariable("orderId") Integer orderId, @RequestBody TrackingInfo trackingInfo) {

        Order order = new Order();
        order.setOrderId(1);
        List<Order> orderList = List.of(order);
        Optional<Order> optionalOrder = Optional.of(orderList.stream().filter(o -> o.getOrderId() == orderId).findFirst().get());

        TrackingInfo update = new TrackingInfo();
        update.setLocation(trackingInfo.getLocation());
        update.setStatus(trackingInfo.getStatus());

        optionalOrder.get().setExternalObjects(update);
        return ResponseEntity.ok(optionalOrder.get());

    }
}
