package com.loiko.alex;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {
        final String productsFileName = "resources/products.csv";
        final String orderItemsFileName = "resources/order_items.csv";
        final String ordersFileName = "resources/orders.csv";
        LocalDate day = LocalDate.of(2021, 01, 21);

        List<Product> products = new CsvToBeanBuilder(new FileReader(productsFileName))
                .withType(Product.class)
                .build()
                .parse();
        products.remove(0);
        System.out.println("List of products has " + products.size() + " elements, including:");
//        products.forEach(System.out::println);
        printSeparator();

        List<OrderItem> orderItems = new CsvToBeanBuilder(new FileReader(orderItemsFileName))
                .withType(OrderItem.class)
                .build()
                .parse();
        orderItems.remove(0);
        System.out.println("List of items has " + orderItems.size() + " elements, including:");
//        orderItems.forEach(System.out::println);
        printSeparator();

        List<Order> orders = new CsvToBeanBuilder(new FileReader(ordersFileName))
                .withType(Order.class)
                .build()
                .parse();
        orders.remove(0);
        System.out.println("List of orders has " + orders.size() + " elements, including:");
//        orders.forEach(System.out::println);
        printSeparator();

        List<Order> ordersRequiredByDate =
                findOrdersFromRequiredDate(orders, day);
        System.out.println("Total orders from required date: " + ordersRequiredByDate.size() + ", including:");
//        ordersRequiredByDate.forEach(System.out::println);
        printSeparator();

        List<OrderItem> orderItemList = findItemsFromRequiredOrders(ordersRequiredByDate, orderItems);
        System.out.println("Total items from required orders: " + orderItemList.size() + ", including:");
        orderItemList.forEach(System.out::println);
        printSeparator();

        System.out.println(findMaxProfit(orderItemList, products));
    }

    private static void printSeparator() {
        System.out.println("********************************************");
    }

    private static List<OrderItem> findItemsFromRequiredOrders(List<Order> orders, List<OrderItem> items) {
        List<OrderItem> result = new ArrayList<>();
        //TODO use streams
        for (OrderItem orderItem : items) {
            for (Order order : orders) {
                if (orderItem.getOrderId().equals(order.getId())) {
                    result.add(OrderItem.builder()
                            .orderId(order.getId())
                            .productId(orderItem.getProductId())
                            .quantity(orderItem.getQuantity())
                            .build());
                }
            }
        }
        return result;
    }

    public static Product findMaxProfit(List<OrderItem> items, List<Product> products) {
        List<Dto> resultDtoList = new ArrayList<>();
        for (OrderItem item : items) {
            for (Product product : products) {
                if (item.getProductId().equals(product.getId())) {
                    Long profit = Long.valueOf(item.getQuantity()) * Long.valueOf(product.getPricePerUnit());
                    resultDtoList.add(Dto.builder()
                            .productId(item.getProductId())
                            .pricePerUnit(product.getPricePerUnit())
                            .quantity(item.getQuantity())
                            .profit(profit)
                            .build());
                }
            }
        }
        //TODO use Optional or List
        Product productResult = null;
        String maxProfitProductId = resultDtoList.stream()
                .max(Comparator.comparing(Dto::getProfit)).get().getProductId();
        for (Product product : products) {
            if(product.getId().equals(maxProfitProductId)) {
                productResult = product;
            }
        }
        System.out.println("Max profit is: " + resultDtoList.stream()
                .max(Comparator.comparing(Dto::getProfit)).get().getProfit() + " from:");
        return productResult;
    }

    private static List<Order> findOrdersFromRequiredDate(List<Order> orders, LocalDate date) {
        List<Order> result = new ArrayList<>();
        //TODO use streams
        for (Order order : orders) {
            String dateString = order.getDateTime();
            LocalDateTime localDateTime = LocalDateTime.parse(dateString);
            LocalDate ld = localDateTime.toLocalDate();
            if (ld.equals(date)) {
                result.add(order);
            }
        }
        return result;
    }
}