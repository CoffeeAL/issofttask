package com.loiko.alex;

import lombok.*;

@Builder
@Getter
@Setter
public class Dto {

    private String productId;
    private String pricePerUnit;
    private String quantity;
    private Long profit;
}