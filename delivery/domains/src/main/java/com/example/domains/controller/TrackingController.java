package com.example.domains.controller;

import com.delivery_company.openapi.api.ApiApi;
import com.delivery_company.openapi.model.TrackingInfoDto;
import com.example.domains.mapper.OrderMapper;
import com.example.domains.mapper.TrackingInfoMapper;
import com.example.domains.service.TrackingInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("v1/tracking/")
public class TrackingController implements ApiApi {

    private final TrackingInfoService trackingInfoService;
    private final TrackingInfoMapper trackingInfoMapper;
    private final OrderMapper orderMapper;

    public TrackingController(TrackingInfoService trackingInfoService, TrackingInfoMapper trackingInfoMapper, OrderMapper orderMapper) {
        this.trackingInfoService = trackingInfoService;
        this.trackingInfoMapper = trackingInfoMapper;
        this.orderMapper = orderMapper;
    }

    /**
     * GET /api/orders/{orderId}/tracking : Retrieve real-time tracking information for an order
     *
     * @param orderId (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<TrackingInfoDto> getTrackingInfo(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(trackingInfoMapper.trackingInfoDto(trackingInfoService.getTrackingInfo(orderId)));
    }

    /**
     * PUT /api/orders/{orderId}/tracking : Update the status and location of an order during delivery
     *
     * @param orderId         (required)
     * @param trackingInfoDto (required)
     * @return OK (status code 200)
     */
    @Override
    public ResponseEntity<TrackingInfoDto> updateTrackingInfo(@PathVariable("orderId") Integer orderId, @RequestBody TrackingInfoDto trackingInfoDto) {
        return ResponseEntity.ok(trackingInfoMapper.trackingInfoDto(trackingInfoService.updateTrackingInfo(orderId,
                trackingInfoMapper.trackingInfoToEntity(trackingInfoDto))));
    }
}
