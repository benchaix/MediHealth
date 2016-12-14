package com.medihealth.billing.service;

import org.junit.Before;

public class BillingServiceTest {

	private BillingService billingService;

	double vaccineCost = 15.0;

	@Before
	public void setUp() throws Exception {
		// test our custom billing service
		billingService = new CustomBillingService();
	}
}
