package com.example.domains.mapper;

import com.delivery_company.openapi.model.TrackingInfoDto;
import com.example.domains.domain.TrackingInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TrackingInfoMapper {

    TrackingInfo trackingInfoToEntity(TrackingInfoDto trackingInfoDto);

    TrackingInfoDto trackingInfoDto(TrackingInfo trackingInfo);
}
