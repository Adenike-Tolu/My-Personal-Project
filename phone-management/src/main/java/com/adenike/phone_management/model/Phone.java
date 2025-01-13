package com.adenike.phone_management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "phone")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name  = "model")
    private  String model;
    @Column(name  = "brand")
    private String brand;
    @Column(name  = "price")
    private Double price;
}
