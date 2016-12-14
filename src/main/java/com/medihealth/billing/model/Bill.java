package com.medihealth.billing.model;

import java.util.List;

public class Bill {

	private Long id;
	private Patient patient;
	private List<Integer> receivedServices;
	private int vaccineNumber = 0;

	public Bill(long id, Patient patient, List<Integer> receivedServices, int vaccineNumber) {
		this.id = id;
		this.patient = patient;
		this.receivedServices = receivedServices;
		this.vaccineNumber = vaccineNumber;
	}

	public long getId() {
		return id;
	}

	public Patient getPatient() {
		return patient;
	}

	public List<Integer> getReceivedServices() {
		return receivedServices;
	}

	public int getVaccineNumber() {
		return vaccineNumber;
	}
}
