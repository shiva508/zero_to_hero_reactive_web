package com.pool.service;

import com.pool.model.ProductModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    public Flux<ProductModel> findAllProducts();

    public Mono<ProductModel> findProductById(String id);

    public Mono<ProductModel> saveProduct(Mono<ProductModel> productModel);

    public Mono<ProductModel> updateProduct(String id, Mono<ProductModel> productModel);

    public Mono<Void> deleteProductById(String id);

    public Flux<ProductModel> findByPriceBetween(Integer minPrice, Integer maxPrice);
}
