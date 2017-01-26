package com.medihealth.billing.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.medihealth.billing.model.Bill;
import com.medihealth.billing.model.MedicalService;
import com.medihealth.billing.model.Patient;
import com.medihealth.billing.service.BillingService;

/**
 * Custom implementation of BillingService
 */
public class CustomBillingService implements BillingService {

  private List<Bill> bills = new ArrayList<>();
  private List<MedicalService> medicalServices = new ArrayList<>();
  private double vaccineCost = 15.0;

  public CustomBillingService() {
    initialize();
  }

  @Override
  public void initialize() {
    medicalServices = new ArrayList<>();
    medicalServices.add(createMedicalService(1, "Diagnosis", 60.0));
    medicalServices.add(createMedicalService(2, "X-Ray", 150.0));
    medicalServices.add(createMedicalService(3, "Blood Test", 78.0));
    medicalServices.add(createMedicalService(4, "ECG", 200.40));
    medicalServices.add(createMedicalService(5, "Vaccine", 27.50));
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
      MedicalService medicalService = getMedicalServiceById(serviceId);

      if (medicalService == null) {
        throw new NullPointerException("medicalService is null");
      }

      boolean isBloodTest = medicalService.getId() == 3;
      totalCost += medicalService.getDiscountCost(patient.getAge(), patient.hasMedihealthInsurance(), isBloodTest);
    }

    if (bill.getVaccineNumber() > 0) {
      totalCost += bill.getVaccineNumber() * 15.0;
    }

    return totalCost;
  }

  public MedicalService createMedicalService(int id, String name, double cost) {
    return new MedicalService(id, name, cost);
  }

  private MedicalService getMedicalServiceById(int id) {
    for (MedicalService medicalService : medicalServices) {
      if (medicalService.getId() == id) {
        return medicalService;
      }
    }

    return null;
  }
}
