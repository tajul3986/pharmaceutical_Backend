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
import com.spring.dao.CartItemsDAO;
import com.spring.model.CartItems;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class CartItemsController implements ICommonController<CartItems>{
	
	
	@Autowired
	private CartItemsDAO cartItemsDAO;

    @GetMapping("/cartItems")
    public List<CartItems> getAll() {
        return cartItemsDAO.getAll();
    }

    @GetMapping("/cartItems/{id}")
    public ResponseEntity<CartItems> getById(@PathVariable(value = "id") Long cartItemsId) {
    	CartItems cartItem = cartItemsDAO.getCartItemsById(cartItemsId);
        return ResponseEntity.ok().body(cartItem);
    }

    @PostMapping("/cartItems")
    public CartItems save(@RequestBody CartItems cartItem) {
        //return employeeDAO.save(employee);
    	return cartItemsDAO.save(cartItem);
    }
    
//    @PostMapping("/CartItems/login")
//    public CartItems login(@RequestBody CartItems CartItems) {
//        //return employeeDAO.save(employee);
//    	return cartItemsDAO.getCartItemsByCartItemsname(CartItems);
//    }

    @PutMapping("/cartItems/{id}")
    public ResponseEntity<CartItems> update(@PathVariable(value = "id") Long cartItemsId,
         @Validated @RequestBody CartItems cartItemsDetails) {
    	CartItems cartItem = cartItemsDAO.getCartItemsById(cartItemsId);
    	cartItem.setProductCode(cartItemsDetails.getProductCode());
    	cartItem.setName(cartItemsDetails.getName());
    	cartItem.setUserId(cartItemsDetails.getUserId());
    	cartItem.setProductId(cartItemsDetails.getProductId());
    	cartItem.setPrice(cartItemsDetails.getPrice());
    	cartItem.setQuantity(cartItemsDetails.getQuantity());
    	cartItem.setTotalPrice(cartItemsDetails.getTotalPrice());
        final CartItems updatedCartItems = cartItemsDAO.save(cartItem);
        return ResponseEntity.ok(updatedCartItems);
    }
    


    @DeleteMapping("/cartItems/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long cartItemsId){
    	CartItems cartItem = cartItemsDAO.getCartItemsById(cartItemsId);
    	cartItemsDAO.delete(cartItem);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
