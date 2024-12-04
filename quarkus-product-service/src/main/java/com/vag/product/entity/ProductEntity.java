package com.vag.product.entity;

import com.vag.product.entity.enumerated.ProductStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Builder(toBuilder = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@FieldNameConstants
// DIFF: No diff :)
@Entity
@Table(name = "product", schema = "quarkus")
@SequenceGenerator(name = "seq_product", schema = "quarkus", sequenceName = "seq_product")
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "brand", length = 100, nullable = false)
    private String brand;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProductStatus productStatus;

    @CreationTimestamp
    @Column(name = "created_on", insertable = true, updatable = false)
    private ZonedDateTime createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on", insertable = false, updatable = true)
    private ZonedDateTime updatedOn;
}
