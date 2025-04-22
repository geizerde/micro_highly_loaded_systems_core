package ru.hpclab.hl.module1.model;

import lombok.*;
import ru.hpclab.hl.module1.api.model.Identifiable;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product implements Identifiable {
    private Long id;
    @Builder.Default
    private UUID identifier = UUID.randomUUID();
    private String name;
    private String category;
    private BigDecimal price;
    private String manufacturer;
}
