package com.yameguun.android.multiplebootbansample;

import android.os.Bundle;
import android.view.Window;

public class SecondActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// NOT TITLE
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_second);
		registerBaseActivityReceiver();
	}
	
	@Override
    protected void onDestroy() {
    	super.onDestroy();
    	unRegisterBaseActivityReceiver();
    }
}
