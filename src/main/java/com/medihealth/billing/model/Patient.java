package com.medihealth.billing.model;

public class Patient {
	private Long id;
	private String firstName;
	private String lastName;
	private int age;
	private boolean medihealthInsurance;

	public Patient(long id, String firstName, String lastName, int age, boolean medihealthInsurance) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.medihealthInsurance = medihealthInsurance;
	}

	public long getId() {
		return id;
	}

	public boolean isMedihealthInsurance() {
		return medihealthInsurance;
	}

	public void setMedihealthInsurance(boolean medihealthInsurance) {
		this.medihealthInsurance = medihealthInsurance;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return getFirstName() + " " + getLastName();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean hasMedihealthInsurance() {
		return medihealthInsurance;
	}
}
