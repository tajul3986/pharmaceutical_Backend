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
import com.spring.model.Category;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pharma")
public class CategoryController implements ICommonController<Category> {
	
	@Autowired
	private CategoryDAO categoryDao;

    @GetMapping("/category")
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<Category> getById(@PathVariable(value = "id") Long categoryId) {
    	Category category = categoryDao.getCategoryById(categoryId);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping("/category")
    public Category save(@RequestBody Category category) {
        //return employeeDAO.save(employee);
    	return categoryDao.save(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<Category> update(@PathVariable(value = "id") Long categoryId,
         @Validated @RequestBody Category categoryDetails) {
    	Category category = categoryDao.getCategoryById(categoryId);
    	category.setName(categoryDetails.getName());
        final Category updatedcategory = categoryDao.save(category);
        return ResponseEntity.ok(updatedcategory);
    }

    @DeleteMapping("/category/{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Long categoryId){
    	Category category = categoryDao.getCategoryById(categoryId);
        categoryDao.delete(category);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
