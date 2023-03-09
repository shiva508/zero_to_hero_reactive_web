package com.pool.service;

import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import com.pool.mapper.ProductMapper;
import com.pool.model.ProductModel;
import com.pool.repository.ProductRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductModel> findAllProducts() {
        return productRepository.findAll().map(productMapper::toProductModel);
    }

    @Override
    public Mono<ProductModel> findProductById(String id) {
        return productRepository.findById(id).map(productMapper::toProductModel);
    }

    @Override
    public Mono<ProductModel> saveProduct(Mono<ProductModel> productModel) {
        return productModel.map(productMapper::toProductEntity)
                .flatMap(product -> productRepository.insert(product))
                .map(productMapper::toProductModel);
    }

    @Override
    public Mono<ProductModel> updateProduct(String id, Mono<ProductModel> productModel) {
        return productRepository.findById(id).flatMap(
                product -> productModel.map(productMapper::toProductEntity)
                        .doOnNext(entity -> entity.setId(id)))
                .flatMap(productRepository::save)
                .map(productMapper::toProductModel);
    }

    @Override
    public Mono<Void> deleteProductById(String id) {
        return productRepository.deleteById(id);
    }

    @Override
    public Flux<ProductModel> findByPriceBetween(Integer minPrice, Integer maxPrice) {
        return productRepository.findByPriceBetween(Range.closed(minPrice, maxPrice))
                .map(productMapper::toProductModel);
    }

}
