package com.spring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "rawmaterial")
@Table(name = "rawmaterial")
public class RawMaterial {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "rm_name")
	private String name;
	
	@Column(name = "rm_code")
	private String code;
	
	@Column(name = "rm_description")
	private String description;
	
	@Column(name = "rm_quantity")
	private int quantity;
	
	@Column(name = "rm_unit")
	private String unit;
	
	@Column(name = "price_perUnit")
	private int pricePerUnit;
	
	@Column(name = "expiry_date")
	private Date expiryDate;
	
	@Column(name = "batch_number")
	private String batchNumber;
	
//	@Column(name = "supplier_name")
//	private String supplierName;
//	
//	@Column(name = "supplier_id")
//	private int supplierId;
//	
//	@Column(name = "purchase_date")
//	private Date purchaseDate;
	
	@Column(name = "storage_location")
	private String storageLocation;
	
	@Column(name = "rm_status")
	private String status;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
//	private String createdByCode;
//	private String createdByName;
//	private String createdAt;
//	
//	
//	private String updatedByCode;
//	private String updatedByName;
//	private String updatedAt;
	
	

	

	

}
