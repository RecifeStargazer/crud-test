package com.teste.crud.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="CLIENT")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy="client")
    private List<Order> orders;

}
