package com.example.test;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartOtherAppTest extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.test_startotherapp);
		
		Button btn1 = (Button) findViewById(R.id.TEST_STARTOTHERAPP_BTN1);
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.baidu.tieba", "com.baidu.tieba.LogoActivity"));
				startActivity(intent);
			}
		});
		
		Button btn2 = (Button) findViewById(R.id.TEST_STARTOTHERAPP_BTN2);
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setComponent(new ComponentName("com.baidu.input", "com.baidu.input.ConfigActivity"));
				startActivity(intent);
				
			}
		});
		
		Button btn3 = (Button) findViewById(R.id.TEST_STARTOTHERAPP_BTN3);
		btn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent shortcut = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");  
		          
			    //��ݷ�ʽ������  
//			    shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, "�ٶ�����");  
//
//			    Intent intent = new Intent(); 
//			    intent.setClass(activity, activity.getClass()); 
//			    intent.setAction("android.intent.action.MAIN"); 
//			    intent.addCategory("android.intent.category.LAUNCHER"); 		
			    
			    //ָ����ǰ��ActivityΪ��ݷ�ʽ�����Ķ���: �� com.everest.video.VideoPlayer  
			    //ע��: ComponentName�ĵڶ�����������������������������+�������������޷�ɾ����ݷ�ʽ  
//			    String appClass = this.getPackageName() + "." +this.getLocalClassName();  
			    ComponentName comp = new ComponentName("com.baidu.input", "com.baidu.input.TiebaActivity");  
			    shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));  
			          
			    sendBroadcast(shortcut);  

				
			}
		});
		
	}
	
}
