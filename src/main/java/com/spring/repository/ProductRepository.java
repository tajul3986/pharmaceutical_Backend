package com.spring.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Product;

@Repository(value = "productRepository")
@Transactional
public interface ProductRepository extends JpaRepository<Product, Long> {
	
//	Product findByName(String name);
	List<Product> findByName(String name);


}
