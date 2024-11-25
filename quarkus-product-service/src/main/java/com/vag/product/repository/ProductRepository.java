package com.vag.product.repository;

import com.vag.product.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.List;

// DIFF:
// PanacheRepository -> will be similar to *Repository(CRUD/etc..) on Spring TODO: add other example of repository classes
//                      keep in mind that quarkus doesn't have anything similar as spring data, so you'll need to implement
//                      your methods.
@ApplicationScoped
@RequiredArgsConstructor
public class ProductRepository implements PanacheRepository<ProductEntity> {

    public List<ProductEntity> findByNameAndBrand(String name, String brand) {
        return find("FROM ProductEntity where name = ?1 and brand = ?2", name, brand).list();
    }

    public List<ProductEntity> findByNameAndBrandNamedParameter(String name, String brand) {
        return find(
                "FROM ProductEntity where name = :name and brand = :brand",
                Parameters.with("name", name)
                        .and("brand", brand)
                        .map()
        ).list();
    }
}
