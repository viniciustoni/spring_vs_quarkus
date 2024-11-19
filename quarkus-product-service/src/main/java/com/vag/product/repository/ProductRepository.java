package com.vag.product.repository;

import com.vag.product.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.Dependent;

// DIFF: @Dependent it'll be similar to @Prototype on spring.
// PS.: I used it in here only to DEMO purposes, please don't do it at home :D
// PanacheRepository -> will be similar to *Repository(CRUD/etc..) on Spring TODO: add other example of repository classes
//                      keep in mind that quarkus doesn't have anything similar as spring data, so you'll need to implement
//                      your methods.
@Dependent
public class ProductRepository implements PanacheRepository<ProductEntity> {

}
