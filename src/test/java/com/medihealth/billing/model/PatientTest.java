package com.medihealth.billing.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PatientTest {
	private Patient patient;

	public static final long PATIENT_ID = 1;
	public static final String PATIENT_FIRST_NAME = "Connor";
	public static final String PATIENT_LAST_NAME = "MacLeod";
	public static final int PATIENT_AGE = 498;
	public static final boolean PATIENT_HAS_INSURANCE = false;

	@Before
	public void setUp() throws Exception {
		patient = new Patient(PATIENT_ID, PATIENT_FIRST_NAME, PATIENT_LAST_NAME, PATIENT_AGE, PATIENT_HAS_INSURANCE);
	}

	@Test
	public void whenGettingPatient1FullName() {
		Assert.assertEquals(PATIENT_FIRST_NAME + " " + PATIENT_LAST_NAME, patient.getFullName());
	}

	@Test
	public void whenGettingPatient1Age() {
		Assert.assertEquals(PATIENT_AGE, patient.getAge());
	}

	@Test
	public void whenGettingPatient1Insurance() {
		Assert.assertEquals(PATIENT_HAS_INSURANCE, patient.hasMedihealthInsurance());
	}
}
