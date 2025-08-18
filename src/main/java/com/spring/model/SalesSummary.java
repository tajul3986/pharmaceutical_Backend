package com.spring.model;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "salessummary")
@Table(name = "salessummary")
public class SalesSummary {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	 	private double todaySales;
	    private double monthlySales;
	    private double yearlySales;
	    @Column(name = "total_amount")
	    private double totalAmount;
	    
	    @OneToMany(mappedBy = "salesSummary", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<TopMedicine> topSellingMedicines;
	    
	    

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public double getTodaySales() {
			return todaySales;
		}

		public double getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(double totalAmount) {
			this.totalAmount = totalAmount;
		}

		public void setTodaySales(double todaySales) {
			this.todaySales = todaySales;
		}

		public double getMonthlySales() {
			return monthlySales;
		}

		public void setMonthlySales(double monthlySales) {
			this.monthlySales = monthlySales;
		}

		public double getYearlySales() {
			return yearlySales;
		}

		public void setYearlySales(double yearlySales) {
			this.yearlySales = yearlySales;
		}

		public List<TopMedicine> getTopSellingMedicines() {
			return topSellingMedicines;
		}

		public void setTopSellingMedicines(List<TopMedicine> topSellingMedicines) {
			this.topSellingMedicines = topSellingMedicines;
		}
	    
	    
}
