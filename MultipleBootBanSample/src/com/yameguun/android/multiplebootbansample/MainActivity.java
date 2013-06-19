package com.yameguun.android.multiplebootbansample;

import java.util.List;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// NOT TITLE
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		registerBaseActivityReceiver();
		
		// �{�^��
		Button button = (Button) this.findViewById(R.id.btn1);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				Intent intent = new Intent();
				intent.setClassName(MainActivity.this,SecondActivity.class.getName());
				startActivity(intent);
			}
		});
		
		ActivityManager activityManager = ((ActivityManager) getSystemService(ACTIVITY_SERVICE));
		List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(5);//����5���擾
		
		boolean flg = false;
		if(taskInfo != null){
		    for (RunningTaskInfo task : taskInfo){
		    	
		        //�G���[���
//		        Log.d("MainActivity","task.id           :"+task.id);
//		        Log.d("MainActivity","task.description  :"+task.description);
//		        Log.d("MainActivity","task.numActivities:"+task.numActivities);
//		        Log.d("MainActivity","task.numRunning   :"+task.numRunning);
//		        Log.d("MainActivity","task.topActivity  :"+task.topActivity.getPackageName());
		    	
		        if(task.topActivity.getPackageName().equals("com.yameguun.android.multiplebootbansample")){
		        	
		        	// ���ɋN�����Ă���Activity�������
		        	if(task.numRunning > 1){
		        		flg = true;
		        	}
		        }
		    }
		}
		
		if (flg) {
			new AlertDialog.Builder(this)
					.setTitle("����")
					.setMessage("�Q�d�N�����Ă���\��������̂ŃA�v�����I�����܂��B")
					.setPositiveButton("�͂�",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {
									closeAllActivities();
								}
							}).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Intent intent = new Intent();
		intent.setClassName(this,AboutActivity.class.getName());
		startActivity(intent);

		return true;
	}
	
	@Override
    protected void onDestroy() {
    	super.onDestroy();
    	unRegisterBaseActivityReceiver();
    }

}
