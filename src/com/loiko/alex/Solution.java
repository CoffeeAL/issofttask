package com.loiko.alex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        LocalDate day = LocalDate.of(2021, 01, 21);
        System.out.println(findMaxProfit(day));
    }

    public static Good findMaxProfit(LocalDate dateOrder) {
        List<Good> orders = new ArrayList<>();
        return orders.stream()
                .max(g -> g.getPrice() * g.getAmount())
                .get();
    }
}
