package com.medihealth.billing.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.medihealth.billing.model.Bill;
import com.medihealth.billing.model.MedicalService;
import com.medihealth.billing.service.BillingService;

public class CustomBillingService implements BillingService {

	private List<Bill> bills = new ArrayList<>();
	private List<MedicalService> medicalServices = new ArrayList<>();
	private double vaccineCost = 0.0;

	@Override
	public void initialize(List<MedicalService> medicalServices, double vaccineCost) {
		this.medicalServices = medicalServices;
		this.vaccineCost = vaccineCost;
	}

	@Override
	public List<MedicalService> getMedicalServices() {
		return medicalServices;
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
		// TODO Auto-generated method stub
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
		return 0;
	}
}
