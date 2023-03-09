package com.pool.repository;

import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.pool.domine.ProductEntity;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveMongoRepository<ProductEntity, String> {

    public Flux<ProductEntity> findByPriceBetween(Range<Integer> range);
}
