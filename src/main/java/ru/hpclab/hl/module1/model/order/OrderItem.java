package ru.hpclab.hl.module1.model.order;

import lombok.*;
import ru.hpclab.hl.module1.api.model.Identifiable;
import ru.hpclab.hl.module1.model.Product;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem implements Identifiable {
    private Long id;
    @Builder.Default
    private UUID identifier = UUID.randomUUID();
    private Product product;
    private int quantity;
}