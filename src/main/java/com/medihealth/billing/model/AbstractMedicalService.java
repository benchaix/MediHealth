package com.medihealth.billing.model;

/**
 * AbstractMedicalService to extend to implement new type of calculation
 */
public abstract class AbstractMedicalService {

	protected int id;
	protected String name;
	protected double cost;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public abstract double getDiscountCost(int patientAge);

	public abstract double getDiscountCost(int patientAge, boolean hasInsurance, boolean isBloodTest);
}
