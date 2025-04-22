package ru.hpclab.hl.module1.entity.postgresql;

import jakarta.persistence.*;
import lombok.*;
import ru.hpclab.hl.module1.api.model.Identifiable;

import java.util.UUID;

@Entity
@Table(name = "t_order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItemEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_order_item")
    @SequenceGenerator(name = "seq_order_item", sequenceName = "seq_order_item", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID identifier;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private int quantity;
}

