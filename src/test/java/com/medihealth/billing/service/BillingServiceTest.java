package com.medihealth.billing.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.medihealth.billing.model.Bill;
import com.medihealth.billing.model.Patient;
import com.medihealth.billing.service.impl.CustomBillingService;

public class BillingServiceTest {

	private BillingService billingService;

	public enum ServiceEnum {
		DIAGNOSIS(1), XRAY(2), BLOODTEST(3), ECG(4), VACCINE(5);

		final int id;

		ServiceEnum(int id) {
			this.id = id;
		}
	}

	@Before
	public void setUp() throws Exception {
		// test our custom billing service
		billingService = new CustomBillingService();

		// prepare fake patient
		Patient patient1 = new Patient(1, "Connor", "MacLeod", 498, false);
		Patient patient2 = new Patient(2, "Benjamin", "Button", 4, true);
		Patient patient3 = new Patient(3, "Leon", "Reno", 68, false);

		// prepare fake services
		List<Integer> services1 = new ArrayList<>();
		services1.add(ServiceEnum.DIAGNOSIS.id);
		services1.add(ServiceEnum.XRAY.id);
		services1.add(ServiceEnum.ECG.id);

		List<Integer> services2 = new ArrayList<>();
		services2.add(ServiceEnum.BLOODTEST.id);
		services2.add(ServiceEnum.VACCINE.id);

		List<Integer> services3 = new ArrayList<>();
		services3.add(ServiceEnum.BLOODTEST.id);
		services3.add(ServiceEnum.ECG.id);

		// add the bills
		Bill bill1 = new Bill(1, patient1, services1, 0);
		billingService.addBill(bill1);

		Bill bill2 = new Bill(2, patient2, services2, 2);
		billingService.addBill(bill2);

		Bill bill3 = new Bill(3, patient3, services3, 1);
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
		Assert.assertEquals(15.0, result, 0);
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

	@Test
	public void whenComputingBillForChildrenUnder5WithBloodTestVaccineAndTwoVaccinesAndInsurance() {
		double result = billingService.computeBill(2);
		// 40% + 15% discount on BloodTest (78£) = 35.1
		// 40% on Vaccine (27.5£) = 16.5
		// +2 Vaccines (2x 15£)
		Assert.assertEquals(81.6, result, 0);
	}

	@Test
	public void whenComputingBillForPeopleBetween65And70WithBloodTestECGAndOneVaccine() {
		double result = billingService.computeBill(3);
		// 60% discount on BloodTest (78£) = 31.2
		// 60% on ECG (200.40£) = 80.16
		// +1 Vaccine (15£)
		Assert.assertEquals(126.36, result, 0);
	}
}
