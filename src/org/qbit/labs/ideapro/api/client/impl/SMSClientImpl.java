package org.qbit.labs.ideapro.api.client.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.qbit.labs.ideapro.api.client.SMSClient;

import hms.kite.samples.api.SdpException;
import hms.kite.samples.api.sms.SmsRequestSender;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsResp;

public class SMSClientImpl implements SMSClient {

	@Override
	public String sendSms(List<String> subscriberIdList, String message) throws MalformedURLException, SdpException {
		MtSmsReq mtSmsReq = new MtSmsReq();
		mtSmsReq.setApplicationId("APP_ID");
		mtSmsReq.setPassword("PASSWORD");
		mtSmsReq.setDestinationAddresses(subscriberIdList);
		mtSmsReq.setMessage(message);
		SmsRequestSender smsRequestSender = new SmsRequestSender(new URL("http://10.0.2.2:7000/sms/send"));
		MtSmsResp mtSmsResp = smsRequestSender.sendSmsRequest(mtSmsReq);
		return mtSmsResp.getStatusDetail();
	}
}
