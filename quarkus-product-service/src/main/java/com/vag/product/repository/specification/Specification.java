package com.vag.product.repository.specification;

import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/**
 * Quarkus doesn't have specification yet, so I create these classes based on spring framework to help me on that.
 *
 * @param <T>
 */
public interface Specification<T> {

    /**
     * Simple static factory method to add some syntactic sugar around a {@link Specification}.
     *
     * @param <T>  the type of the {@link Root} the resulting {@literal Specification} operates on.
     * @param spec can be {@literal null}.
     * @return guaranteed to be not {@literal null}.
     */
    static <T> Specification<T> where(@Nullable Specification<T> spec) {
        return spec == null ? (root, query, builder) -> null : spec;
    }

    /**
     * ANDs the given {@link Specification} to the current one.
     *
     * @param other can be {@literal null}.
     * @return The conjunction of the specifications
     */
    default Specification<T> and(@Nullable Specification<T> other) {
        return SpecificationComposition.composed(this, other, CriteriaBuilder::and);
    }


    /**
     * Creates a WHERE clause for a query of the referenced entity in form of a {@link Predicate} for the given
     * {@link Root} and {@link CriteriaQuery}.
     *
     * @param root            must not be {@literal null}.
     * @param query           can be {@literal null} to allow overrides that accept {@link jakarta.persistence.criteria.CriteriaDelete} which is an {@link jakarta.persistence.criteria.AbstractQuery} but no {@link CriteriaQuery}.
     * @param criteriaBuilder must not be {@literal null}.
     * @return a {@link Predicate}, may be {@literal null}.
     */
    @Nullable
    Predicate toPredicate(Root<T> root, @Nullable CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder);


}
