package com.example.orderservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final RestTemplate restTemplate = new RestTemplate();

  @Value("${inventory.service.url:http://inventory-service:8082}")
  private String inventoryServiceUrl;

  @Value("${payment.service.url:http://payment-service:8083}")
  private String paymentServiceUrl;

  @PostMapping
  public String createOrder(@RequestBody Order order) throws InterruptedException {
    // Simulate database I/O (700ms)
    Thread.sleep(700);

    // Call Inventory Service
    String inventoryResponse = restTemplate.getForObject(
        inventoryServiceUrl + "/inventory/check?productId=" + order.productId(), String.class);

    // Call Payment Service
    String paymentResponse = restTemplate.getForObject(
        paymentServiceUrl + "/payments/process?amount=" + order.amount(), String.class);

    return "Order created: " + inventoryResponse + ", " + paymentResponse;
  }
}

record Order(String productId, double amount) {
}
