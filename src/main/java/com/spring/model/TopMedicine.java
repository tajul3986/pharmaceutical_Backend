package com.spring.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "topmedicine")
@Table(name = "topmedicine")
public class TopMedicine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
    private int quantitySold;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_summary_id") // foreign key column
    private SalesSummary salesSummary;

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

	public int getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	public SalesSummary getSalesSummary() {
		return salesSummary;
	}

	public void setSalesSummary(SalesSummary salesSummary) {
		this.salesSummary = salesSummary;
	}
    
    
}
