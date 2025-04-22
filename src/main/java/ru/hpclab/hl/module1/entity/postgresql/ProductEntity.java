package ru.hpclab.hl.module1.entity.postgresql;

import jakarta.persistence.*;
import lombok.*;
import ru.hpclab.hl.module1.api.model.Identifiable;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "t_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product")
    @SequenceGenerator(name = "seq_product", sequenceName = "seq_product", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID identifier;

    @Column(nullable = false)
    private String name;

    @Column(name = "category", nullable = false)
    private String category;

    @JoinColumn(name = "manufacturer", nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private BigDecimal price;
}

