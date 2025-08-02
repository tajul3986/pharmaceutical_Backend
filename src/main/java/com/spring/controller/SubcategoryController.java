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
import com.spring.dao.CategoryDAO;
import com.spring.dao.SubcategoryDAO;
import com.spring.model.Category;
import com.spring.model.Subcategory;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class SubcategoryController implements ICommonController<Subcategory> {
	
	@Autowired
	private SubcategoryDAO subcategoryDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	
	
	@GetMapping("/getMeta")
	public Map<String, Object> getAllMeta() {

		List<Subcategory> subcategory = subcategoryDAO.getAll();
		List<Category> category = categoryDAO.getAll();
		

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("category", category);
		map.put("subcategory", subcategory);
		
		return map;
	}
	
	
	
	

	@Override
    @GetMapping("/subcategory")
    public List<Subcategory> getAll() {
        return subcategoryDAO.getAll();
    }

	@Override
    @GetMapping("/subcategory/{id}")
    public ResponseEntity<Subcategory> getById(@PathVariable(value = "id") Long subcategoryId) {
    	Subcategory subcategory = subcategoryDAO.getCategoryById(subcategoryId);
        return ResponseEntity.ok().body(subcategory);
    }

	@Override
    @PostMapping("/subcategory")
    public Subcategory save(@RequestBody Subcategory subcategory) {
        //return employeeDAO.save(employee);
    	return subcategoryDAO.save(subcategory);
    }

	@Override
    @PutMapping("/subcategory/{id}")
    public ResponseEntity<Subcategory> update(@PathVariable(value = "id") Long subcategoryId,
         @Validated @RequestBody Subcategory subcategoryDetails) {
    	Subcategory subcategory = subcategoryDAO.getCategoryById(subcategoryId);
    	subcategory.setName(subcategoryDetails.getName());
    	subcategory.setCategoryId(subcategoryDetails.getCategoryId());
    	subcategory.setCategoryName(subcategoryDetails.getCategoryName());
        final Subcategory updatedcategory = subcategoryDAO.save(subcategory);
        return ResponseEntity.ok(updatedcategory);
    }

	@Override
    @DeleteMapping("/subcategory/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long categoryId){
    	Subcategory subcategory = subcategoryDAO.getCategoryById(categoryId);
    	subcategoryDAO.delete(subcategory);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
