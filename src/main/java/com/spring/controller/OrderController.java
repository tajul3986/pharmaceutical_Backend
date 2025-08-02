package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.spring.dao.OrderDAO;
import com.spring.enums.OrderStatus;
import com.spring.model.Order;
import com.spring.model.OrderItems;
import com.spring.model.Product;
import com.spring.model.SalesSummary;
import com.spring.repository.ProductRepository;

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
    
//    @GetMapping("/sales-summary")
//    public ResponseEntity<SalesSummary> getSalesSummary() {
//        SalesSummary summary = new SalesSummary();
//
//        summary.setTodaySales(orderDAO.getTodaySales());      // total sales today
//        summary.setMonthlySales(orderDAO.getMonthlySales());  // total sales current month
//        summary.setYearlySales(orderDAO.getYearlySales());    // total sales current year
//        summary.setTopSellingMedicines(orderDAO.getTopSellingMedicines());
//
//        return ResponseEntity.ok(summary);
//    }

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
    
    
	
//    new section approval 
    
    // ✅ Check approval status (customer side)
//    @GetMapping("/orders/check-approval/{orderId}")
//    public ResponseEntity<Boolean> checkApproval(@PathVariable Long orderId) {
//        Order order = orderDAO.getById(orderId);
//        if (order == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(order.isApproved());
//    }
//
//    // ✅ Approve an order (admin side)
//    @PutMapping("/orders/approve/{orderId}")
//    public ResponseEntity<String> approveOrder(@PathVariable Long orderId) {
//        Order order = orderDAO.getById(orderId);
//        System.out.println("orderId:" + orderId);
//        if (order == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
//
//        order.setApproved(true);
//        order.setStatus(OrderStatus.APPROVED);
//        orderDAO.update(order);
//        return ResponseEntity.ok("Order Approved");
//    }
//    
//    @PutMapping("/orders/reject/{orderId}")
//    public ResponseEntity<String> rejectOrder(@PathVariable Long orderId) {
//        Order order = orderDAO.getById(orderId);
//        if (order == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
//
//        order.setApproved(false);
//        order.setStatus(OrderStatus.REJECTED);
//        orderDAO.update(order);
//        return ResponseEntity.ok("Order Rejected");
//    }
//	
	
	
	
	
	
	
	
	
	
//
//    @Autowired
//    private OrderDAO orderDAO;
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    // 🔹 CREATE ORDER
//    @PostMapping("/orders")
//    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
//        for (OrderItems item : order.getItems()) {
//            List<Product> products = productRepository.findByName(item.getName());
//            if (!products.isEmpty()) {
//                Product product = products.get(0);
//                int newStock = product.getStock() - item.getQuantity();
//                product.setStock(Math.max(newStock, 0));
//                productRepository.save(product);
//            }
//        }
//
//        // 🔥 ESSENTIAL STEP — Set the back-reference
//        order.setItems(order.getItems());
//
//        if (order.getStatus() == null || order.getStatus().isEmpty()) {
//            order.setStatus("PENDING_APPROVAL");
//        }
//
//        Order saved = orderDAO.save(order);
//        return ResponseEntity.ok(saved);
//    }
//
//
//    // 🔹 GET ALL ORDERS
//    @GetMapping("/orders")
//    public ResponseEntity<List<Order>> getAllOrders() {
//        return ResponseEntity.ok(orderDAO.getAll());
//    }
//
//    // 🔹 GET BY ID
//    @GetMapping("/orders/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
//        Order order = orderDAO.getById(id);
//        if (order == null) return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(order);
//    }
//
//    // 🔹 UPDATE ORDER (Approve/Reject support)
//    @PutMapping("/orders/{id}")
//    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
//        Order existing = orderDAO.getById(id);
//        if (existing == null) return ResponseEntity.notFound().build();
//
//        updatedOrder.setId(id);
//
//        // If items are present, update reference
//        if (updatedOrder.getItems() != null) {
//            for (OrderItems item : updatedOrder.getItems()) {
//                item.setOrder(updatedOrder);
//            }
//        }
//
//        Order updated = orderDAO.update(updatedOrder);
//        return ResponseEntity.ok(updated);
//    }
//
//    // 🔹 DELETE ORDER
//    @DeleteMapping("/orders/{id}")
//    public ResponseEntity<String> deleteOrder(@PathVariable Long id) {
//        Order order = orderDAO.getById(id);
//        if (order == null) return ResponseEntity.notFound().build();
//
//        orderDAO.delete(order);
//        return ResponseEntity.ok("Order deleted.");
//    }
}
