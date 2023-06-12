package com.example.domains.mapper;

import com.delivery_company.openapi.model.ProductDto;
import com.example.domains.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProductMapper {
    Product productToEntity(ProductDto productDto);

    ProductDto productToDto(Product product);
}
