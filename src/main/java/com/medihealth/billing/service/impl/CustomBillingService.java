package com.medihealth.billing.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.medihealth.billing.model.Bill;
import com.medihealth.billing.model.MedicalPrestation;
import com.medihealth.billing.model.Patient;
import com.medihealth.billing.service.BillingService;

/**
 * Custom implementation of BillingService
 */
public class CustomBillingService implements BillingService {

	private List<Bill> bills = new ArrayList<>();
	private List<MedicalPrestation> medicalPrestations = new ArrayList<>();
	private double vaccineCost = 0.0;

	@Override
	public void initialize(List<MedicalPrestation> medicalPrestations, double vaccineCost) {
		this.medicalPrestations = medicalPrestations;
		this.vaccineCost = vaccineCost;
	}

	@Override
	public List<MedicalPrestation> getMedicalPrestations() {
		return medicalPrestations;
	}

	@Override
	public double getVaccineCost() {
		return vaccineCost;
	}

	@Override
	public List<Bill> getBills() {
		return bills;
	}

	@Override
	public void addBill(Bill bill) {
		bills.add(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		// TODO to implement
	}

	@Override
	public Bill getBill(long billId) {
		for (Bill bill : bills) {
			if (bill.getId() == billId) {
				return bill;
			}
		}
		return null;
	}

	@Override
	public void removeBill(long billId) {
		int index = 0;
		for (Bill bill : bills) {
			if (bill.getId() == billId) {
				bills.remove(index);
				return;
			}
			index++;
		}
	}

	@Override
	public double computeBill(long billId) {
		Bill bill = getBill(billId);
		Patient patient = bill.getPatient();

		double totalCost = 0;

		for (int serviceId : bill.getReceivedServices()) {
			MedicalPrestation medicalPrestation = getMedicalPrestationById(serviceId);
			boolean isBloodTest = medicalPrestation.getId() == 3;
			totalCost += medicalPrestation.getDiscountCost(patient.getAge(), patient.hasMedihealthInsurance(),
					isBloodTest);
		}

		if (bill.getVaccineNumber() > 0) {
			totalCost += bill.getVaccineNumber() * 15.0;
		}

		return totalCost;
	}

	private MedicalPrestation getMedicalPrestationById(int id) {
		for (MedicalPrestation medicalPrestation : medicalPrestations) {
			if (medicalPrestation.getId() == id) {
				return medicalPrestation;
			}
		}

		return null;
	}
}
