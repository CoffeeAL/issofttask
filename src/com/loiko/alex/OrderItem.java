package com.loiko.alex;

import com.opencsv.bean.CsvBindByPosition;
import lombok.*;

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
}