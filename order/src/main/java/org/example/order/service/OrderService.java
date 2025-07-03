package org.example.order.service;

import org.example.order.dto.OrderResponseDto;
import org.example.order.entity.Customer;
import org.example.order.entity.Order;
import org.example.order.entity.Product;
import org.example.order.repository.OrderRepository;
import org.example.order.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class OrderService {

    @Autowired
    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    public OrderService(RestTemplate restTemplate, OrderRepository orderRepository) {
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(int id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order updateOrder(int id, Order orderDetails) {
        Order order = getOrderById(id);
        order.setDescription(orderDetails.getDescription());
        order.setCustomerId(orderDetails.getCustomerId());
        order.setProductId(orderDetails.getProductId());
        return orderRepository.save(order);
    }

    public void deleteOrder(int id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }

    public boolean existsById(int id) {
        return orderRepository.existsById(id);
    }

    public OrderResponseDto orderToOrderResponseDto(Order order){
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setDescription(order.getDescription());
        RestClient<Product> productRestClient = new RestClient<>("http://PRODUCT/product/1", restTemplate);
        Product product = productRestClient.get(Product.class);
        orderResponseDto.setProduct(product);
        RestClient<Customer> customerRestClient = new RestClient<>("http://CUSTOMER/customer/1", restTemplate);
        Customer customer = customerRestClient.get(Customer.class);
        orderResponseDto.setCustomer(customer);
        return orderResponseDto;
    }
}
