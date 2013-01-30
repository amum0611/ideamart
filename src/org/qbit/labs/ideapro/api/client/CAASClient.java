package org.qbit.labs.ideapro.api.client;

import java.net.MalformedURLException;

import hms.kite.samples.api.SdpException;

public interface CAASClient {

	String getBalance(String subscriberID) throws MalformedURLException, SdpException;

	String directDebit(String subscriberID, String amount) throws MalformedURLException, SdpException;
}
