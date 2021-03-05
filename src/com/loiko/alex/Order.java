package com.loiko.alex;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private int id;
    private LocalDate dateOrder;
    private List<Good> goods;

    public Order(int id, LocalDate dateOrder) {
        this.id = id;
        this.dateOrder = dateOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<Good> getGoods() {
        return goods;
    }

    public void setGoods(List<Good> goods) {
        this.goods = goods;
    }
}
