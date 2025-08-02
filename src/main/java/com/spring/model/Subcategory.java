package com.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "subcategory")
@Table(name = "subcategory")
public class Subcategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "subcategory_name")
	private String name;
	
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "category_name")
	private String categoryName;
	
	
//	private String createdByCode;
//	private String createdByName;
//	private String createdAt;
//	
//	
//	private String updatedByCode;
//	private String updatedByName;
//	private String updatedAt;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
