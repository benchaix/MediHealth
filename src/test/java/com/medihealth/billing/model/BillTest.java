package com.medihealth.billing.model;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BillTest {

  public static final long BILL_ID = 1;
  public static final int VACCINE_NUMBER = 0;
  Bill bill;

  @Before
  public void setUp() throws Exception {
    // create a new patient
    Patient patient = PatientTest.createDefaultPatient();

    // create received services list
    List<Integer> receivedServices = new ArrayList<>();
    receivedServices.add(1); // diagnosis service
    receivedServices.add(4); // ECG service

    // create the bill
    bill = new Bill(BILL_ID, patient, receivedServices, VACCINE_NUMBER);
  }

  @Test
  public void whenRetrievingBillPatient() {
    Patient result = bill.getPatient();
    Assert.assertNotNull(result);
  }

  @Test
  public void whenRetrievingBillReceivedServices() {
    List<Integer> result = bill.getReceivedServices();
    Assert.assertEquals(result.size(), 2);
  }

  @Test
  public void whenRetrievingBillPatientFirstName() {
    Assert.assertEquals(PatientTest.PATIENT_FIRST_NAME, bill.getPatient().getFirstName());
  }

  @Test
  public void whenRetrievingBillFirstReceivedServideId() {
    Assert.assertEquals(1, bill.getReceivedServices().get(0).intValue());
  }

  @Test
  public void whenRetrievingBillVaccineNumber() {
    Assert.assertEquals(VACCINE_NUMBER, bill.getVaccineNumber());
  }
}
