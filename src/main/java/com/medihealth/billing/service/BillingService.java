package com.medihealth.billing.service;

import java.util.List;

import com.medihealth.billing.model.Bill;
import com.medihealth.billing.model.MedicalPrestation;

public interface BillingService {

	void initialize(List<MedicalPrestation> medicalPrestations, double vaccineCost);

	List<MedicalPrestation> getMedicalPrestations();

	double getVaccineCost();

	List<Bill> getBills();

	void addBill(Bill bill);

	void updateBill(Bill bill);

	Bill getBill(long billId);

	void removeBill(long billId);

	double computeBill(long billId);
}
