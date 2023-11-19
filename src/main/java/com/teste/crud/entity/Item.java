package com.teste.crud.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

@Entity
@Table(name="ITEMS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name="items", nullable=false)
    private Order order;

    @Column(unique = true, nullable = false)
    private Long controlNumber;

    @Column(nullable = false)
    private LocalDate registrationDate;

    @Column(nullable = false)
    private String productName;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private Integer quantity;
}
