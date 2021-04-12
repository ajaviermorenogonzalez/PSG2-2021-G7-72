package org.springframework.samples.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="causes")
public class Cause extends BaseEntity{
	
	private String name;
	
	@Column(name="total_budget_achieved")
	private Integer totalBudgetAchived;
	
	@Column(name="budget_Achieved")
	private Integer budgetAchieved;
	
	@Column(name="budget_target")
	private Integer budgetTarget;
	
	private String organization;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalBudgetAchived() {
		return totalBudgetAchived;
	}

	public void setTotalBudgetAchived(Integer totalBudgetAchived) {
		this.totalBudgetAchived = totalBudgetAchived;
	}

	public Integer getBudgetAchieved() {
		return budgetAchieved;
	}

	public void setBudgetAchieved(Integer budgetAchieved) {
		this.budgetAchieved = budgetAchieved;
	}

	public Integer getBudgetTarget() {
		return budgetTarget;
	}

	public void setBudgetTarget(Integer budgetTarget) {
		this.budgetTarget = budgetTarget;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}
	
	

}
