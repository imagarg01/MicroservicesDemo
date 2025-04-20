package com.example.inventoryservice;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @GetMapping("/check")
    public String checkInventory(@RequestParam String productId) throws InterruptedException {
        // Simulate database I/O (500ms)
        Thread.sleep(500);
        return "Stock available for product: " + productId;
    }
}
