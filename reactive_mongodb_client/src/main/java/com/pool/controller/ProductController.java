package com.pool.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.pool.model.ProductModel;
import com.pool.service.ProductService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("all")
    public Flux<ProductModel> findAllProducts() {
        return productService.findAllProducts();
    }

    @GetMapping("productid/{id}")
    public Mono<ProductModel> findProductById(@PathVariable("id") String id) {
        return productService.findProductById(id);
    }

    @PostMapping("saveproduct")
    public Mono<ProductModel> saveProduct(@RequestBody Mono<ProductModel> productModel) {
        return productService.saveProduct(productModel);
    }

    @PutMapping("update/{id}")
    public Mono<ProductModel> updateProduct(@PathVariable String id, @RequestBody Mono<ProductModel> productModel) {
        return productService.updateProduct(id, productModel);
    }

    @DeleteMapping("delete/{id}")
    public Mono<Void> deleteProductById(@PathVariable String id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("pricerange")
    public Flux<ProductModel> findByPriceBetween(@RequestParam("minPrice") Integer minPrice,
            @RequestParam("maxPrice") Integer maxPrice) {
        return productService.findByPriceBetween(minPrice, maxPrice);
    }
}
