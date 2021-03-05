package com.loiko.alex;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        LocalDate day = LocalDate.of(2021, 01, 21);
        System.out.println(findMaxProfit(day));
    }

    public static Product findMaxProfit(LocalDate dateOrder) {
        List<Product> orders = new ArrayList<>();
        List<Product> good = orders.stream()
                .sorted(Comparator.comparing((g -> g.getAmount() * g.getPrice())))
                .collect(Collectors.toList());
        return good.get(good.size()-1);
    }
}
