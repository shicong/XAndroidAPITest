/**
 * 
 */
package com.example.test;

import com.ruoogle.base.FrameLayoutNoticeBar;
import com.ruoogle.base.FrameLayoutNoticeBar.NoticeBarOnReceive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author shicong
 */
public class FrameLayoutNoticeBarActivity extends Activity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_framelayoutnoticebar);
		
		FrameLayoutNoticeBar v = (FrameLayoutNoticeBar) findViewById(R.id.ID_TEST_FRAMELAYOUTNOTICEBAR_POP_VIEW);
		TextView tesTextView = new TextView(this);
		tesTextView.setBackgroundColor(0xffff0000);
		tesTextView.setText("通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试通知测试");
		v.setNoticeBarView(tesTextView);
		
		/*发送通知的Button*/
		Button sBtnSendBroadCast = (Button) findViewById(R.id.ID_TEST_FRAMELAYOUTNOTICEBAR_BTN);
		sBtnSendBroadCast.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(NoticeBarOnReceive.ACTION_BROADCAST_RECEIVER_NOTICE);
				LocalBroadcastManager.getInstance(FrameLayoutNoticeBarActivity.this).sendBroadcast(intent);
			}
		});
	}
}
