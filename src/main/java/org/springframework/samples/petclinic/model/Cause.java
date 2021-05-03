package org.springframework.samples.petclinic.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="causes")
public class Cause extends BaseEntity{
	
	//Propiedades
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String description;
	
	@Column(name="total_budget_achieved")
	private Double totalBudgetAchived;

	@NotNull
	@Column(name="budget_target")
	private Double budgetTarget;
	
	@NotBlank
	private String organization;
	
	@Column(name="isClosed")
	private Boolean isClosed = false;
	
	@OneToMany(cascade = CascadeType.ALL)
	private Set<Donation> donations;

	//Getters & Setters
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public Double getTotalBudgetAchived() {
		return totalBudgetAchived;
	}

	public void setTotalBudgetAchived(Double totalBudgetAchived) {
		this.totalBudgetAchived = totalBudgetAchived;
	}

	public Double getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Double budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	public Boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(Boolean isClosed) {
		this.isClosed = isClosed;
	}

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	//toString
	
	@Override
	public String toString() {
		return "Cause [name=" + name + ", description=" + description + ", totalBudgetAchived=" + totalBudgetAchived
				+ ", budgetTarget=" + budgetTarget + ", organization=" + organization + ", isClosed=" + isClosed
				+ ", donations=" + donations + "]";
	}



}
