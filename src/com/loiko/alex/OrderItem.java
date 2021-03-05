package com.loiko.alex;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderItem {

    @CsvBindByPosition(position = 0)
    private String orderId;

    @CsvBindByPosition(position = 1)

    private List<Order> orderList;

    private List<Product> productList;

    private int quantity;
}