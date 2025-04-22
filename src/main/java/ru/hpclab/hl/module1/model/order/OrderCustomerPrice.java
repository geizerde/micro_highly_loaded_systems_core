package ru.hpclab.hl.module1.model.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCustomerPrice {
    @JsonProperty("order_id")
    private Long orderId;

    @JsonProperty("customer_id")
    private Long CustomerId;

    @JsonProperty("total_price")
    private BigDecimal totalPrice;
}
