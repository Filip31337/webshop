package com.brajnovic.webshop.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;

    private String title;

    private String description;

    private String saleStatus;

    private String category;

    private Long price;

    private String createdBy;

    private String createdAt;

    private String location;

    private String ownedStatus;

    private Integer viewCount;

}
