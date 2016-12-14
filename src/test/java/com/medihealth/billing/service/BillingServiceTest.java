package com.medihealth.billing.service;

import org.junit.Before;

import com.medihealth.billing.service.impl.CustomBillingService;

public class BillingServiceTest {

	private BillingService billingService;

	double vaccineCost = 15.0;

	@Before
	public void setUp() throws Exception {
		// test our custom billing service
		billingService = new CustomBillingService();
	}
}
