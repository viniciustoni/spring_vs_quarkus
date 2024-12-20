package com.vag.product.domain.specification;

import com.vag.product.entity.ProductEntity;
import com.vag.product.entity.enumerated.ProductStatus;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class ProductSpecification {

    public Specification<ProductEntity> activeProducts() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ProductEntity.Fields.productStatus), ProductStatus.ACTIVE);
    }

    public Specification<ProductEntity> brand(String brand) {
        if (brand == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ProductEntity.Fields.brand), brand);
    }

    public Specification<ProductEntity> name(String name) {
        if (name == null) {
            return null;
        }

        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(ProductEntity.Fields.name), name);
    }

}
