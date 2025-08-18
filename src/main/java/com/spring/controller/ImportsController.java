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
import com.spring.dao.ImportsDAO;
import com.spring.model.Category;
import com.spring.model.Imports;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class ImportsController implements ICommonController<Imports>{
	
	@Autowired
	private ImportsDAO importsDAO;

    @GetMapping("/imports")
    public List<Imports> getAll() {
        return importsDAO.getAll();
    }

    @GetMapping("/imports/{id}")
    public ResponseEntity<Imports> getById(@PathVariable(value = "id") Long importsId) {
    	Imports imports = importsDAO.getImportsById(importsId);
        return ResponseEntity.ok().body(imports);
    }
    
    
    @PostMapping("/imports")
    public ResponseEntity<Imports> addImport(@RequestBody Imports importData) {
        System.out.println("Received: " + importData); // Debug check
        return ResponseEntity.ok(importsDAO.save(importData));
    		
    }

//
//    @PostMapping("/imports")
//    public Imports save(@RequestBody Imports imports) {
//        //return employeeDAO.save(employee);
//    	return importsDAO.save(imports);
//    	
//    }

    @PutMapping("/imports/{id}")
    public ResponseEntity<Imports> update(@PathVariable(value = "id") Long importsId,
         @Validated @RequestBody Imports importsDetails) {
    	Imports imports = importsDAO.getImportsById(importsId);
    	imports.setSellerId(importsDetails.getSellerId());
    	imports.setMaterialId(importsDetails.getMaterialId());
    	imports.setSellerName(importsDetails.getSellerName());    	
    	imports.setMaterialName(importsDetails.getMaterialName());
    	imports.setQuantity(importsDetails.getQuantity());
    	imports.setUnitPrice(importsDetails.getUnitPrice());
    	imports.setTotalPrice(importsDetails.getTotalPrice());
    	imports.setImportDate(importsDetails.getImportDate()); 	
    	
        final Imports updatedimports = importsDAO.save(imports);
        return ResponseEntity.ok(updatedimports);
    }

    @DeleteMapping("/imports/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long importsId){
    	Imports imports = importsDAO.getImportsById(importsId);
    	importsDAO.delete(imports);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

	@Override
	public Imports save(Imports t) {
		// TODO Auto-generated method stub
		return null;
	}


}
