package com.adenike.phone_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneResponseDto {
    private Long id;
    private String model;
    private String brand;
    private Double price;
}
