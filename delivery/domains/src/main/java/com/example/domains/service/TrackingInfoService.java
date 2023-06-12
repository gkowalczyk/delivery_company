package com.example.domains.service;

import com.example.domains.domain.Order;
import com.example.domains.domain.TrackingInfo;
import com.example.domains.repository.OrderRepository;
import com.example.domains.repository.TrackingInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class TrackingInfoService {

    private final TrackingInfoRepository trackingInfoRepository;
    private final OrderRepository orderRepository;

    public TrackingInfoService(TrackingInfoRepository trackingInfoRepository, OrderRepository orderRepository) {
        this.trackingInfoRepository = trackingInfoRepository;
        this.orderRepository = orderRepository;
    }

    public TrackingInfo getTrackingInfo(Integer orderId) {
        Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new));
        return optionalOrder.get().getTrackingInfo();
    }

    public TrackingInfo updateTrackingInfo(Integer orderId, TrackingInfo trackingInfo) {
        Order order = orderRepository.findById(orderId).orElseThrow(EntityNotFoundException::new);
        order.setTrackingInfo(new TrackingInfo(trackingInfo.getLocation(), trackingInfo.getStatus()));
        return trackingInfoRepository.save(order.getTrackingInfo());
    }
}