package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.dao.OrderDAO;
import com.spring.model.Order;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class OrderController {
	
	@Autowired
    private OrderDAO orderDAO;

    
    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
    	System.out.println("Hi");
        Order saved = orderDAO.save(order);
        
        return ResponseEntity.ok(saved);      
    }
    
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(orderDAO.getAll());
    }

    
    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderDAO.getById(id);
        if (order == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existing = orderDAO.getById(id);
        if (existing == null) return ResponseEntity.notFound().build();

        order.setId(id);
        Order updated = orderDAO.update(order);
        return ResponseEntity.ok(updated);   
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
        Order order = orderDAO.getById(id);
        if (order == null) return ResponseEntity.notFound().build();

        orderDAO.delete(order);
        return ResponseEntity.ok("Order deleted.");
    }
    
}
