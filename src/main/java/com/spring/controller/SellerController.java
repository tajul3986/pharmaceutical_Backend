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

import com.spring.dao.SellerDAO;
import com.spring.model.Seller;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class SellerController {
	
	@Autowired
	private SellerDAO sellerDao;

    @GetMapping("/seller")
    public List<Seller> getAll() {
        return sellerDao.getAll();
    }

    @GetMapping("/seller/{id}")
    public ResponseEntity<Seller> getById(@PathVariable(value = "id") Long sellerId) {
    	Seller seller = sellerDao.getUserById(sellerId);
        return ResponseEntity.ok().body(seller);
    }

    @PostMapping("/seller")
    public Seller save(@RequestBody Seller seller) {
        //return employeeDAO.save(employee);
    	return sellerDao.save(seller);
    }

    @PutMapping("/seller/{id}")
    public ResponseEntity<Seller> update(@PathVariable(value = "id") Long sellerId,
         @Validated @RequestBody Seller sellerDetails) {
    	Seller seller = sellerDao.getUserById(sellerId);
    	seller.setName(sellerDetails.getName());
    	seller.setContactEmail(sellerDetails.getContactEmail());
    	seller.setContactPhone(sellerDetails.getContactPhone());
    	seller.setAddress(sellerDetails.getAddress());
    	
    	
        final Seller updatedseller = sellerDao.save(seller);
        return ResponseEntity.ok(updatedseller);
    }

    @DeleteMapping("/seller/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long sellerId){
    	Seller seller = sellerDao.getUserById(sellerId);
    	sellerDao.delete(seller);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
