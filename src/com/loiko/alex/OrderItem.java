package com.loiko.alex;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class OrderItem {

    @CsvBindByPosition(position = 0)
    private String orderId;

    @CsvBindByPosition(position = 1)
    private String productId;

    @CsvBindByPosition(position = 2)
    private String quantity;

    OrderItem(Order order, Product product) {
        this.orderId = order.getId();
        this.productId = product.getId();
    }
    //    @CsvBindByPosition(position = 1)
//    private List<Order> orderList;
//
//    private List<Product> productList;
//
//    public OrderItem(String orderId, List<Order> orderList, List<Product> productList) {
//        this.orderId = orderId;
//        this.orderList = orderList;
//        this.productList = productList;
//    }
}