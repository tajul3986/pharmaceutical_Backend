package com.spring.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.model.Category;


@Repository(value = "categoryRepository")
@Transactional
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
