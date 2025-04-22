package ru.hpclab.hl.module1.entity.postgresql;

import jakarta.persistence.*;
import lombok.*;
import ru.hpclab.hl.module1.api.model.Identifiable;
import ru.hpclab.hl.module1.model.order.PaymentStatus;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "t_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_orders")
    @SequenceGenerator(name = "seq_orders", sequenceName = "seq_orders", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID identifier;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "payment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> orderItems;
}

