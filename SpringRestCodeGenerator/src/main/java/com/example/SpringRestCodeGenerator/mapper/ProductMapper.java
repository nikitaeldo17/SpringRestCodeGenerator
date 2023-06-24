package com.example.SpringRestCodeGenerator.mapper;

import com.example.SpringRestCodeGenerator.dto.ProductDTO;
import com.example.SpringRestCodeGenerator.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product, ProductDTO> {
    @Override
    @Mapping(target = "id", ignore = true)
    Product asEntity(ProductDTO dto);
}