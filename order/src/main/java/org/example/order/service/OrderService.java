package org.example.order.service;

import org.example.order.dto.OrderResponseDto;
import org.example.order.entity.Customer;
import org.example.order.entity.Order;
import org.example.order.entity.Product;
import org.example.order.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderService {

    @Autowired
    private final RestTemplate restTemplate;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Order getOrderById(int id){
        return new Order(id,"ma commande pour les vacances",2,5);
    }

    public OrderResponseDto orderToOrderResponseDto(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setDescription(order.getDescription());
        RestClient<Product> productRestClient = new RestClient<>("http://PRODUCT/product/42", restTemplate);
        Product product = productRestClient.get(Product.class);
        orderResponseDto.setProduct(product);
        RestClient<Customer> customerRestClient = new RestClient<>("http://CUSTOMER/customer/2", restTemplate);
        Customer customer = customerRestClient.get(Customer.class);
        orderResponseDto.setCustomer(customer);
        return orderResponseDto;
    }
}
