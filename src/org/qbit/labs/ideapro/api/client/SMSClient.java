package org.qbit.labs.ideapro.api.client;

import java.net.MalformedURLException;
import java.util.List;

import hms.kite.samples.api.SdpException;

public interface SMSClient {

	String sendSms(List<String> subscriberIdList, String message) throws MalformedURLException, SdpException;
}
