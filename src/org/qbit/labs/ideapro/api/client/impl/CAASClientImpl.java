package org.qbit.labs.ideapro.api.client.impl;

import java.net.MalformedURLException;
import java.net.URL;

import org.qbit.labs.ideapro.api.client.CAASClient;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.caas.ChargingRequestSender;
import hms.kite.samples.api.caas.messages.DirectDebitRequest;
import hms.kite.samples.api.caas.messages.DirectDebitResponse;
import hms.kite.samples.api.caas.messages.QueryBalanceRequest;
import hms.kite.samples.api.caas.messages.QueryBalanceResponse;

public class CAASClientImpl implements CAASClient {

	@Override
	public String getBalance(String subscriberID) throws MalformedURLException, SdpException {
		QueryBalanceRequest queryBalanceRequest = new QueryBalanceRequest();
		queryBalanceRequest.setApplicationId("APP_ID");
		queryBalanceRequest.setPassword("PASSWORD");
		queryBalanceRequest.setSubscriberId(subscriberID);
		ChargingRequestSender chargingRequestSender = new ChargingRequestSender(new URL(
				"http://10.0.2.2:7000/caas/get/balance"));
		QueryBalanceResponse queryBalanceResponse = chargingRequestSender.sendQueryBalanceRequest(queryBalanceRequest);
		return queryBalanceResponse.getChargeableBalance();
	}

	@Override
	public String directDebit(String subscriberID, String amount) throws MalformedURLException, SdpException {
		DirectDebitRequest directDebitRequest = new DirectDebitRequest();
		directDebitRequest.setApplicationId("APP_ID");
		directDebitRequest.setPassword("PASSWORD");
		directDebitRequest.setSubscriberId(subscriberID);
		directDebitRequest.setAmount(amount);
		directDebitRequest.setExternalTrxId("SOME_UNIQUE_ID");
		ChargingRequestSender chargingRequestSender = new ChargingRequestSender(new URL(
				"http://10.0.2.2:7000/caas/direct/debit"));
		DirectDebitResponse directDebitResponse = chargingRequestSender.sendDirectDebitRequest(directDebitRequest);
		return directDebitResponse.getStatusDetail();
	}
}
