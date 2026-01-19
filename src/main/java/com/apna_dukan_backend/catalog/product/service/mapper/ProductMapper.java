package com.apna_dukan_backend.catalog.product.service.mapper;

import com.apna_dukan_backend.catalog.product.model.ProductEntity;
import com.apna_dukan_backend.catalog.product.model.domain.Product;
import com.apna_dukan_backend.catalog.product.model.dto.ProductAdminResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public Product toDomain(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        Product product = new Product();
        product.setProductId(entity.getProductId());
        product.setProductGroupId(entity.getProductGroupId());
        product.setName(entity.getName());
        product.setBrand(entity.getBrand());
        product.setDescription(entity.getDescription());
        product.setCode(entity.getCode());
        product.setPrimaryImageUrl(entity.getPrimaryImageUrl());
        product.setImageUrls(entity.getImageUrls());
        product.setEnabled(entity.isEnabled());
        product.setCreatedAt(entity.getCreatedAt());
        product.setUpdatedAt(entity.getUpdatedAt());
        return product;
    }

    public ProductEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }
        ProductEntity entity = new ProductEntity();
        entity.setProductId(product.getProductId());
        entity.setProductGroupId(product.getProductGroupId());
        entity.setName(product.getName());
        entity.setBrand(product.getBrand());
        entity.setDescription(product.getDescription());
        entity.setCode(product.getCode());
        entity.setPrimaryImageUrl(product.getPrimaryImageUrl());
        entity.setImageUrls(product.getImageUrls());
        entity.setEnabled(product.isEnabled());
        entity.setCreatedAt(product.getCreatedAt());
        entity.setUpdatedAt(product.getUpdatedAt());
        return entity;
    }

    public ProductAdminResponseDto toAdminDto(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ProductAdminResponseDto(
                entity.getProductId(),
                entity.getProductGroupId(),
                entity.getName(),
                entity.getBrand(),
                entity.getDescription(),
                entity.getCode(),
                entity.getPrimaryImageUrl(),
                entity.getImageUrls(),
                entity.isEnabled(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }

    public List<ProductAdminResponseDto> toAdminDtoList(List<ProductEntity> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
                .map(this::toAdminDto)
                .collect(Collectors.toList());
    }
}





