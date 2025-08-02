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

import com.spring.dao.RawMaterialDAO;
import com.spring.model.RawMaterial;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class RawMaterialController {


	@Autowired
	private RawMaterialDAO rawMaterialDAO;

    @GetMapping("/rawmaterial")
    public List<RawMaterial> getAll() {
        return rawMaterialDAO.getAll();
    }

    @GetMapping("/rawmaterial/{id}")
    public ResponseEntity<RawMaterial> getById(@PathVariable(value = "id") Long rawmaterialId) {
    	RawMaterial rawmaterial = rawMaterialDAO.getRawMaterialById(rawmaterialId);
        return ResponseEntity.ok().body(rawmaterial);
    }

    @PostMapping("/rawmaterial")
    public RawMaterial save(@RequestBody RawMaterial rawmaterial) {
        //return employeeDAO.save(employee);
    	return rawMaterialDAO.save(rawmaterial);
    }

    @PutMapping("/rawmaterial/{id}")
    public ResponseEntity<RawMaterial> update(@PathVariable(value = "id") Long rawmaterialId,
         @Validated @RequestBody RawMaterial rawmaterialDetails) {
    	RawMaterial rawmaterial = rawMaterialDAO.getRawMaterialById(rawmaterialId);
    	rawmaterial.setName(rawmaterialDetails.getName());
    	rawmaterial.setCode(rawmaterialDetails.getCode());
    	rawmaterial.setDescription(rawmaterialDetails.getDescription());
    	rawmaterial.setQuantity(rawmaterialDetails.getQuantity());
    	rawmaterial.setUnit(rawmaterialDetails.getUnit());
    	rawmaterial.setPricePerUnit(rawmaterialDetails.getPricePerUnit());
    	rawmaterial.setExpiryDate(rawmaterialDetails.getExpiryDate()); 	
    	rawmaterial.setBatchNumber(rawmaterialDetails.getBatchNumber()); 	
//    	rawmaterial.setSupplierName(rawmaterialDetails.getSupplierName()); 	
//    	rawmaterial.setSupplierId(rawmaterialDetails.getSupplierId()); 	
//    	rawmaterial.setPurchaseDate(rawmaterialDetails.getPurchaseDate()); 	
    	rawmaterial.setStorageLocation(rawmaterialDetails.getStorageLocation()); 	
    	rawmaterial.setStatus(rawmaterialDetails.getStatus()); 	
    	 	
    	
        final RawMaterial updatedrawmaterial = rawMaterialDAO.save(rawmaterial);
        return ResponseEntity.ok(updatedrawmaterial);
    }

    @DeleteMapping("/rawmaterial/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long rawmaterialId){
    	RawMaterial rawmaterial = rawMaterialDAO.getRawMaterialById(rawmaterialId);
    	rawMaterialDAO.delete(rawmaterial);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
