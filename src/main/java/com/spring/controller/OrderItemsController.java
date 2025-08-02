package com.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.common.ICommonController;
import com.spring.dao.OrderItemsDAO;
import com.spring.model.OrderItems;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class OrderItemsController implements ICommonController<OrderItems> {
	
	@Autowired
	private OrderItemsDAO orderItemsDAO;

    @GetMapping("/orderitems")
    public List<OrderItems> getAll() {
        return orderItemsDAO.getAll();
    }

    @GetMapping("/orderitems/{id}")
    public ResponseEntity<OrderItems> getById(@PathVariable(value = "id") Long orderitemsId) {
    	OrderItems orderitems = orderItemsDAO.getById(orderitemsId);
        return ResponseEntity.ok().body(orderitems);
    }

    @PostMapping("/orderitems")
    public OrderItems save(@RequestBody OrderItems orderitems) {
        //return employeeDAO.save(employee);
    	return orderItemsDAO.save(orderitems);
    }

    @PutMapping("/orderitems/{id}")
    public ResponseEntity<OrderItems> update(@PathVariable(value = "id") Long orderitemsId,
         @Validated @RequestBody OrderItems orderitemsIdDetails) {
    	OrderItems orderitems = orderItemsDAO.getById(orderitemsId);
    	orderitems.setName(orderitemsIdDetails.getName());
    	orderitems.setQuantity(orderitemsIdDetails.getQuantity());
    	orderitems.setPrice(orderitemsIdDetails.getPrice());
        final OrderItems updatedorderitems = orderItemsDAO.save(orderitems);
        return ResponseEntity.ok(updatedorderitems);
    }

    @DeleteMapping("/orderitems/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long orderitemsId){
    	OrderItems orderitems = orderItemsDAO.getById(orderitemsId);
    	orderItemsDAO.delete(orderitems);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
