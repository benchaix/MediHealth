package com.medihealth.billing.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MedicalPrestationTest {

	MedicalPrestation medicalPrestation;
	static final int SERVICE_ID = 1;
	static final String SERVICE_NAME = "Diagnosis";
	static final double SERVICE_COST = 60.0;

	@Before
	public void setUp() throws Exception {
		medicalPrestation = new MedicalPrestation(SERVICE_ID, SERVICE_NAME, SERVICE_COST);
	}

	@Test
	public void whenRetrievingServiceName() {
		Assert.assertEquals(SERVICE_NAME, medicalPrestation.getName());
	}

	@Test
	public void whenRetrievingServiceCost() {
		Assert.assertEquals(SERVICE_COST, medicalPrestation.getCost(), 0);
	}

	@Test
	public void whenComputingCostForPeopleBetween65And70() {
		// receive a 60% discount
		// must be 24 for a person of 67 years old
		Assert.assertEquals(24, medicalPrestation.getDiscountCost(67), 0);
	}

	@Test
	public void whenComputingCostForPeopleOver70() {
		// receive a 90% discount
		// must be 6 for a person of 80 years old
		Assert.assertEquals(6, medicalPrestation.getDiscountCost(80), 0);
	}

	@Test
	public void whenComputingCostForPeopleBetween65And70WithBloodTestAndInsurance() {
		// receive a 60% discount + a 15% discount
		// must be 6 for a person of 67 years old
		MedicalPrestation bloodTestService = new MedicalPrestation(3, "BloodTest", 78);
		Assert.assertEquals(19.5, bloodTestService.getDiscountCost(67, true, true), 0);
	}

	@Test
	public void whenComputingCostForPeopleOver70WithBloodTestAndInsurance() {
		// receive a 90% discount + a 15% discount
		// must be 0 for a person of 80 years old
		MedicalPrestation bloodTestService = new MedicalPrestation(3, "BloodTest", 78);
		Assert.assertEquals(0, bloodTestService.getDiscountCost(80, true, true), 0);
	}
}
