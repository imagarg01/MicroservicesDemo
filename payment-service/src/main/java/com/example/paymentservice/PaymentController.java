package com.example.paymentservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @GetMapping("/process")
    public String processPayment(@RequestParam double amount) {
        // Simulate CPU-bound processing (300ms)
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 300) {
            // Busy loop to simulate CPU work
            Math.sqrt(Math.random());
        }
        return "Payment processed: $" + amount;
    }
}
