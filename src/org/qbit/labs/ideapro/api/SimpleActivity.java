package org.qbit.labs.ideapro.api;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import hms.kite.samples.api.SdpException;
import org.qbit.labs.ideapro.api.client.CAASClient;
import org.qbit.labs.ideapro.api.client.SMSClient;
import org.qbit.labs.ideapro.api.client.impl.CAASClientImpl;
import org.qbit.labs.ideapro.api.client.impl.SMSClientImpl;

import java.net.MalformedURLException;
import java.util.Arrays;

public class SimpleActivity extends Activity {

	private EditText subscriberIdEditView;
	private TextView resultTextView;
	public static final String TAG = "SimpleActivity";
	public static final String CURRENT_LOGGED_IN_USER = "+94777123456";
	public static final double LEVEL_2_VALUE = 10;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		subscriberIdEditView = (EditText) findViewById(R.id.subscriberIdEditText);
		resultTextView = (TextView) findViewById(R.id.resultTextView);
		Button unlockRequestButton = (Button) findViewById(R.id.unlockRequestButton);
		Button sendRequestButton = (Button) findViewById(R.id.sendRequestButton);
		unlockRequestButton.setOnClickListener(getUnlockRequestListeners());
		sendRequestButton.setOnClickListener(getSendRequestListener());
	}

	private View.OnClickListener getUnlockRequestListeners() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				CAASClient caasClient = new CAASClientImpl();
				double balance;
				String response;
				try {
					balance = Double.parseDouble(caasClient.getBalance(CURRENT_LOGGED_IN_USER));
					if (LEVEL_2_VALUE <= balance) {
						response = caasClient.directDebit(CURRENT_LOGGED_IN_USER, String.valueOf(LEVEL_2_VALUE));
					} else {
						response = "You don't have enough credits";
					}
				} catch (MalformedURLException e) {
					Log.e(TAG, e.getMessage(), e);
					response = e.getMessage();
				} catch (SdpException e) {
					Log.e(TAG, e.getMessage(), e);
					response = e.getMessage();
				}
				resultTextView.setText(response);
			}
		};
	}

	private View.OnClickListener getSendRequestListener() {
		return new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				SMSClient smsClient = new SMSClientImpl();
				String subscriberID = subscriberIdEditView.getText().toString();
				String message = "Are you brave enough to challenge me?";
				String response;
				try {
					response = smsClient.sendSms(Arrays.asList(subscriberID), message);
				} catch (MalformedURLException e) {
					Log.e(TAG, e.getMessage(), e);
					response = e.getMessage();
				} catch (SdpException e) {
					Log.e(TAG, e.getMessage(), e);
					response = e.getMessage();
				}
				resultTextView.setText(response);
			}
		};
	}
}
