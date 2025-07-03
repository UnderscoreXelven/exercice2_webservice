package org.example.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.order.entity.Customer;
import org.example.order.entity.Product;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderResponseDto {

    private int id;
    private String description;
    private Customer customer;
    private Product product;

}
