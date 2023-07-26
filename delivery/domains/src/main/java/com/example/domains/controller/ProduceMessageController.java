package com.example.domains.controller;

import com.delivery_company.openapi.model.OrderDto;
import com.example.domains.exception.MyFileNotFoundException;
import com.example.domains.mapper.OrderMapper;
import com.example.domains.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.jms.Queue;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/jms")
@Slf4j
public class ProduceMessageController {
    private static final Logger logger = LoggerFactory.getLogger(ProduceMessageController.class);
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final JmsTemplate jmsTemplate;
    private final Queue queue;
    private final DatabaseToCsvHandler databaseToCsvHandler;
    private final FileStorageService fileStorageService;
    private final EmailService emailService;


    public ProduceMessageController(OrderService orderService, OrderMapper orderMapper, JmsTemplate jmsTemplate, Queue queue, DatabaseToCsvHandler databaseToCsvHandler, FileStorageService fileStorageService, EmailService emailService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.jmsTemplate = jmsTemplate;
        this.queue = queue;
        this.databaseToCsvHandler = databaseToCsvHandler;
        this.fileStorageService = fileStorageService;
        this.emailService = emailService;
    }

    @PostMapping(value = "/api/order/{customerId}")
    public ResponseEntity<Void> sendOrderHistoryToQueueForCustomerId(@PathVariable("customerId") Integer customerId,
                                                                     @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startSearch,
                                                                     @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endSearch) {
        try {
            List<OrderDto> list = orderService.sendReportToQueue(customerId, startSearch, endSearch)
                    .stream().map(orderMapper::orderToDto)
                    .collect(Collectors.toList());
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            String json = mapper.writeValueAsString(list);
            jmsTemplate.convertAndSend(queue, json);
            logger.info("Send data to MQ");
        } catch (EntityNotFoundException | JsonProcessingException jsonProcessingException) {
            jsonProcessingException.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{customerId}/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Integer customerId, @PathVariable String fileName, HttpServletRequest request) throws IOException {

        String contentType = null;
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        try {
            databaseToCsvHandler.exportDataToCsv(customerId);
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
            emailService.sendToCustomer(customerId, fileName);
        } catch (MyFileNotFoundException myFileNotFoundException) {
            logger.info("Could not determine file type." + myFileNotFoundException.getMessage());
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
