package com.medihealth.billing.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MedicalPrestation extends AbstractMedicalPrestation {

	public MedicalPrestation(int id, String name, double cost) {
		this.id = id;
		this.name = name;
		this.cost = cost;
	}

	@Override
	public double getDiscountCost(int patientAge) {
		return getDiscountCost(patientAge, false, false);
	}

	/**
	 * Use custom calculation based on patient benefits
	 */
	@Override
	public double getDiscountCost(int patientAge, boolean hasInsurance, boolean isBloodTest) {

		double discount = cost;

		if (patientAge < 5) { // 40% discount
			discount = cost - (cost * 40 / 100);

		} else if (patientAge >= 65 && patientAge < 70) { // 60% discount
			discount = cost - (cost * 60 / 100);

		} else if (patientAge >= 70) { // 90% discount
			discount = cost - (cost * 90 / 100);
		}

		discount = BigDecimal.valueOf(discount).setScale(2, RoundingMode.HALF_UP).doubleValue();

		if (isBloodTest && hasInsurance) { // +15% discount
			double superDiscount = cost * 15 / 100;
			discount = BigDecimal.valueOf(discount).subtract(BigDecimal.valueOf(superDiscount)).doubleValue();
		}

		if (discount < 0)
			return 0;

		return discount;
	}
}
