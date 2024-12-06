package com.vag.product.repository;

import com.vag.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// DIFF: here we have spring data, which our repository can be an interface. It'll generate the queries based on
// the method names or via @Query annotation.
// Also, pay attention that we are extending JpaSpecificationExecutor, thanks to this interface we will have the jpa
// methods that supports specification.
// Since that we are extending on JpaRepository we don't need to add the @Repository annotation,
// it'll be included on ComponentScan by default
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    // DIFF: Thanks to spring data, we can create our query based on the method name
    List<ProductEntity> findByBrandAndName(String brand, String name);

    // DIFF: However in case we need, we can use @Query which by default is JQL. In case, we want native query we can:
    // 1. Use: @NativeQuery
    // 2. Use @Query(nativeQuery = true, value = "my native query")
    @Query("FROM ProductEntity WHERE brand = :brand AND name = :name")
    List<ProductEntity> findByBrandAndNameQuery(@Param("brand") String brand, @Param("name") String name);

}
