package com.loiko.alex;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Dto {

    private String date;
    private String orderId;
    private String productId;
    private String pricePerUnit;
    private String quantity;
    private String profit;

    Dto(Product product, Order order, OrderItem orderItem) {
        this.date = order.getDateTime();
        this.orderId = order.getId();
        this.productId = product.getId();
        this.pricePerUnit = product.getPricePerUnit();
        this.quantity = orderItem.getQuantity();
        Integer profitInt = Integer.valueOf(this.quantity) * Integer.valueOf(this.pricePerUnit);
        this.profit = profitInt.toString();
    }
}