package com.vag.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@RequiredArgsConstructor
// DIFF: No diff :)
@Entity
@Table(name = "product")
@SequenceGenerator(name = "seq_product", sequenceName = "seq_product")
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "seq_product", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "brand", length = 100, nullable = false)
    private String brand;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @CreationTimestamp
    @Column(name = "created_at", insertable = true, updatable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = false, updatable = true)
    private ZonedDateTime updatedAt;
}
