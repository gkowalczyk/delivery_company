package com.example.domains.jms;

import com.delivery_company.openapi.model.OrderDto;
import com.example.domains.domain.Order;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class JmsConsumer  {
    private static final Logger logger = LoggerFactory.getLogger(JmsConsumer.class);
   // @JmsListener(destination = "${active-mq.queue}")
    public void onMessage(String message) {

           logger.info("Received Message from Queue: "+ message);

    }
}