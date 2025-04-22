package ru.hpclab.hl.module1.entity.postgresql;

import jakarta.persistence.*;
import lombok.*;
import ru.hpclab.hl.module1.api.model.Identifiable;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "t_customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity implements Identifiable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer")
    @SequenceGenerator(name = "seq_customer", sequenceName = "seq_customer", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private UUID identifier;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @PrePersist
    public void prePersist() {
        if (registrationDate == null) {
            registrationDate = LocalDate.now();
        }
    }
}

