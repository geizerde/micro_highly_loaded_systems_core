package ru.hpclab.hl.module1.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemDTO {
    private Long id;
    private UUID identifier;
    private Long productId;
    private UUID productIdentifier;
    private int quantity;
}