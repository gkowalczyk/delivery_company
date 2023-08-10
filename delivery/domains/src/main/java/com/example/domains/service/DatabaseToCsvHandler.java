package com.example.domains.service;

import com.example.domains.domain.Order;
import com.example.domains.exception.MyFileNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import java.io.*;
import java.util.List;
import static java.util.Optional.ofNullable;

@Service
public class DatabaseToCsvHandler {

    private final OrderService orderService;
    private final static String ID = "ID:";
    private final static String CUSTOMER_ID = "CUSTOMER ID:";
    private final static String TRACKING_INFO = "TRACKING INFO:";
    private final static String TRACKING_INFO_LOCATION = "LOCATION:";
    private final static String TRACKING_INFO_STATUS = "STATUS OF ORDER:";
    private final static String ORDER_DATE = "DATE OF ORDER:";
    private final static String FILE_NAME = "Order.csv";

    public DatabaseToCsvHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    public void exportDataToCsv(Integer customerId) throws IOException {

        List<Order> getTrackingInfoList = ofNullable(orderService.getTrackingInfo(customerId)).orElseThrow(EntityNotFoundException::new);
        String[] HEADERS = {ID, CUSTOMER_ID, TRACKING_INFO, ORDER_DATE};
        FileWriter fileWriter = new FileWriter(FILE_NAME);
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS)
                .build();

        try (CSVPrinter printer = new CSVPrinter(fileWriter, csvFormat)) {
            for (Order order : getTrackingInfoList) {
                try {
                    printer.printRecord(ID + order.getId(), "\n"
                            + CUSTOMER_ID + order.getCustomerId(), "\n"
                            + TRACKING_INFO_LOCATION + order.getTrackingInfo().getLocation()
                            + "\n" + TRACKING_INFO_STATUS + order.getTrackingInfo().getStatus(), "\n"
                            + ORDER_DATE + order.getDate());
                } catch (IOException ioException) {
                   throw new MyFileNotFoundException("File couldn't be printed" + ioException.getMessage());
                }
            }
        }
    }
}


