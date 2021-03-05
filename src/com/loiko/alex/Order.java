package com.loiko.alex;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class Order {

    @CsvBindByPosition(position = 0)
    private String id;

    @CsvBindByPosition(position = 1)
    private LocalDate dateTime;

    private List<Product> products;

    public Order(String id, LocalDate dateTime) {
        this.id = id;
        this.dateTime = dateTime;
    }
}