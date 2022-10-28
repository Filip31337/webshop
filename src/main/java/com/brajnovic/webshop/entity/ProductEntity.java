package com.brajnovic.webshop.entity;

import com.brajnovic.webshop.model.Product;
import com.brajnovic.webshop.model.User;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
@SequenceGenerator(name = "SEQ_PRODUCT", sequenceName = "SEQ_PRODUCT", allocationSize = 1)
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PRODUCT")
    @Column(name = "ID", nullable = false)
    private Long id;

    private String title;

    private String description;

    @Column(name = "SALE_STATUS")
    private String saleStatus;

    private String category;

    private Long price;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_AT")
    private String createdAt;

    private String location;

    @Column(name= "OWNED_STATUS")
    private String ownedStatus;

    @Column(name = "VIEW_COUNT")
    private Integer viewCount;

    public static Product convertToProduct(ProductEntity productEntity) {
        return Product.builder()
                .id(productEntity.getId())
                .title(productEntity.getTitle())
                .category(productEntity.getCategory())
                .createdAt(productEntity.getCreatedAt())
                .createdBy(productEntity.getCreatedBy())
                .description(productEntity.getDescription())
                .location(productEntity.getLocation())
                .ownedStatus(productEntity.getOwnedStatus())
                .price(productEntity.getPrice())
                .saleStatus(productEntity.getSaleStatus())
                .viewCount(productEntity.getViewCount())
                .build();

    }
}
