package com.loiko.alex;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException, ParseException {
        final String productsFileName = "resources/products.csv";
        final String orderItemsFileName = "resources/order_items.csv";
        final String ordersFileName = "resources/orders.csv";
        LocalDate day = LocalDate.of(2021, 01, 21);

        CSVReader productsReader = new CSVReader(new FileReader(productsFileName));
        CSVReader orderItemsReader = new CSVReader(new FileReader(orderItemsFileName));
        CSVReader ordersReader = new CSVReader(new FileReader(ordersFileName));

        List<Product> products = new CsvToBeanBuilder(new FileReader(productsFileName))
                .withType(Product.class)
                .build()
                .parse();
        products.remove(0);
//        Integer i = Integer.valueOf(products.get(0).getPricePerUnit());

        List<OrderItem> orderItems = new CsvToBeanBuilder(new FileReader(orderItemsFileName))
                .withType(OrderItem.class)
                .build()
                .parse();
        orderItems.remove(0);
//        orderItems.forEach(System.out::println);

        List<Order> orders = new CsvToBeanBuilder(new FileReader(ordersFileName))
                .withType(Order.class)
                .build()
                .parse();
        orders.remove(0);

        List<Order> ordersRequiredByDate =
                ordersFromRequiredDate(orders, LocalDate.of(2021, 01,21));
        ordersRequiredByDate.forEach(System.out::println);

        //  OrderId  Date
//        Map<String, Date> ordersAndDates = new HashMap<>();
//        for (Order order : orders) {
//            String dateString = order.getDateTime();
//            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
//            Date docDate = format.parse(dateString);
//            ordersAndDates.put(order.getId(), docDate);
//        }
//
//        List<Dto> dtoList = new ArrayList<>();


//        for (OrderItem orderItem : orderItems) {
//            String date = null;
//            String orderId = orderItem.getOrderId();
//            String productId = orderItem.getProductId();
//            String pricePerUnit = null;
//            String quantity = null;
//            Integer profit = Integer.valueOf(pricePerUnit) * Integer.valueOf(quantity);
//            String profitAsString = profit.toString();
//            dtoList.add(new Dto(date, orderId, productId, pricePerUnit, quantity, profitAsString));
//        }
//        List<Product> products214 = parseProductCsv(productsFileName);
    }
//
//    public static Product findMaxProfit(LocalDate dateOrder) {
//        List<Product> orders = new ArrayList<>();
//        List<Product> good = orders.stream()
//                .sorted(Comparator.comparing((g -> g.getAmount() * g.getPrice())))
//                .collect(Collectors.toList());
//        return good.get(good.size()-1);
//    }

    private static List<Order> ordersFromRequiredDate(List<Order> orders, LocalDate date) throws ParseException {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            String dateString = order.getDateTime();
            LocalDateTime localDateTime = LocalDateTime.parse(dateString);
            LocalDate ld = localDateTime.toLocalDate();
            if(ld.equals(date)) {
                result.add(order);
            }
        }
        return result;
    }

    private static List<Product> parseProductCsv(String filePath) throws IOException {
        List<Product> products = new ArrayList<>();
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        for (String fileLine : fileLines) {
            String[] splitedText = fileLine.split(",");
            List<String> columnList = new ArrayList<>();
            for (int i = 0; i < splitedText.length; i++) {
                if (isColumnPart(splitedText[i])) {
                    String lastText = columnList.get(columnList.size() - 1);
                    columnList.set(columnList.size() - 1, lastText + "," + splitedText[i]);
                } else {
                    columnList.add(splitedText[i]);
                }
            }
            Product product = new Product();
            int pricePerUnit = 0;
            try {
                pricePerUnit = Integer.valueOf(columnList.get(2));
                product.builder()
                        .id(columnList.get(0))
                        .name(columnList.get(1))
                        .pricePerUnit(columnList.get(2))
                        .build();
                products.add(product);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return products;
    }

    private static boolean isColumnPart(String text) {
        String trimText = text.trim();
        return trimText.indexOf("\"") == trimText.lastIndexOf("\"") && trimText.endsWith("\"");
    }
}