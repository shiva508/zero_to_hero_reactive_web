package com.pool.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.pool.domine.ProductEntity;
import com.pool.model.ProductModel;

@Component
public class ProductMapper {

    public ProductEntity toProductEntity(ProductModel productModel) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(productModel, productEntity);
        return productEntity;
    }

    public ProductModel toProductModel(ProductEntity productEntity) {
        ProductModel productModel = new ProductModel();
        BeanUtils.copyProperties(productEntity, productModel);
        return productModel;
    }
}
