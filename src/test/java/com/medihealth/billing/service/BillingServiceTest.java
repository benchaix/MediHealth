package com.medihealth.billing.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.medihealth.billing.model.Bill;
import com.medihealth.billing.model.MedicalService;
import com.medihealth.billing.model.Patient;
import com.medihealth.billing.service.impl.CustomBillingService;

public class BillingServiceTest {

	private BillingService billingService;

	double vaccineCost = 15.0;

	@Before
	public void setUp() throws Exception {
		// test our custom billing service
		billingService = new CustomBillingService();

		// populate medical services list with data sample
		List<MedicalService> medicalServices = new ArrayList<>();
		medicalServices.add(new MedicalService(1, "Diagnosis", 60.0));
		medicalServices.add(new MedicalService(2, "X-Ray", 150.0));
		medicalServices.add(new MedicalService(3, "Blood Test", 78.0));
		medicalServices.add(new MedicalService(4, "ECG", 200.40));
		medicalServices.add(new MedicalService(5, "Vaccine", 27.50));

		billingService.initialize(medicalServices, vaccineCost);

		// prepare fake patient
		Patient patient1 = new Patient(1, "Connor", "MacLeod", 498, false);
		Patient patient2 = new Patient(2, "Benjamin", "Button", 4, true);
		Patient patient3 = new Patient(3, "Leon", "Reno", 68, false);

		// prepare received services
		List<Integer> receivedServices1 = new ArrayList<>();
		receivedServices1.add(1); // Diagnosis
		receivedServices1.add(2); // X-RAY
		receivedServices1.add(4); // ECG

		// prepare received services
		List<Integer> receivedServices2 = new ArrayList<>();
		receivedServices2.add(3); // Blood Test
		receivedServices2.add(5); // Vaccine

		// prepare received services
		List<Integer> receivedServices3 = new ArrayList<>();
		receivedServices3.add(3); // Blood Test
		receivedServices3.add(4); // ECG

		// add the bills
		Bill bill1 = new Bill(1, patient1, receivedServices1, 0);
		billingService.addBill(bill1);

		Bill bill2 = new Bill(2, patient2, receivedServices2, 2);
		billingService.addBill(bill2);

		Bill bill3 = new Bill(3, patient3, receivedServices3, 1);
		billingService.addBill(bill3);
	}

	@Test
	public void whenRetrievingMedicalServices() {
		int size = billingService.getMedicalServices().size();
		Assert.assertEquals(size, 5);
	}

	@Test
	public void whenRetrievingVaccineCost() {
		double result = billingService.getVaccineCost();
		Assert.assertEquals(vaccineCost, result, 0);
	}

	@Test
	public void whenRetrievingBills() {
		List<Bill> result = billingService.getBills();
		Assert.assertEquals(3, result.size());
	}

	// tests on bill calculation

	@Test
	public void whenComputingBillForPeopleOver70WithDiagnosisXRayAndECG() {
		double result = billingService.computeBill(1);
		// 90% discount on Diagnosis (60£) = 6
		// 90% discount on X-RAY (150£) = 15
		// 90% discount on ECG (200.40£) = 20.04
		Assert.assertEquals(41.04, result, 0);
	}

}
