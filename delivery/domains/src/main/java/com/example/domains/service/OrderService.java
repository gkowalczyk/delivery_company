package com.example.domains.service;

import com.example.domains.domain.Order;
import com.example.domains.domain.TrackingInfo;
import com.example.domains.repository.OrderRepository;
import com.example.domains.repository.TrackingInfoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TrackingInfoRepository trackingInfoRepository;

    public OrderService(OrderRepository orderRepository, TrackingInfoRepository trackingInfoRepository) {
        this.orderRepository = orderRepository;
        this.trackingInfoRepository = trackingInfoRepository;
    }

    public Order getOrder(Integer id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


    public Order addOrder(final Order order) {
        return orderRepository.save(order);
    }
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public List<Order> getTrackingInfo(Integer id) {
        return orderRepository.findAll().stream()
                .filter(x -> x.getCustomerId() == id)
                .collect(Collectors.toList());
    }
}
