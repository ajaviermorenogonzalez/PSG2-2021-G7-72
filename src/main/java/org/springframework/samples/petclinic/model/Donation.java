package org.springframework.samples.petclinic.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Table(name="donations")
public class Donation extends BaseEntity {

	//Propiedades
	
	@NotBlank
	private String donorName;
	
	@NotNull
	private Double amount;
	
	private LocalDate date;

	
	//Getters & Setters
	public String getDonorName() {
		return donorName;
	}

	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
//	public Cause getCause() {
//		return cause;
//	}
//
//	public void setCause(Cause cause) {
//		this.cause = cause;
//	}
	
//	//Relaciones
//	
//	@ManyToOne
//	@JoinColumn(name = "cause_id")
//	private Cause cause;

	//ToString
	@Override
	public String toString() {
		return "Donation [donorName=" + donorName + ", amount=" + amount + ", date=" + date + "]";
	}


	
	
	
}
