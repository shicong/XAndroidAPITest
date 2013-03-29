/**
 * 
 */
package com.example.test.clocklistview;

import com.example.test.R;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * @author shicong
 */
public class ClockListViewActivity extends Activity{

	private ClockListView mClockListView;
	private String[] mItemClockStrings = {
		"08:30",
		"09:00",
		"09:30",
		"12:00",
		"18:17",
		"19:30",
		"20:11",
		"22:00",
		"22:35",
		"23:00",
		"01:30",
		"02:00",
		"02:30",
		"03:00",
		"03:17",
		"03:30",
		"04:11",
		"05:00",
		"06:35",
		"07:00",
	};
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_clocklistview);
		
		mClockListView = (ClockListView) findViewById(R.id.ID_TEST_CLOCKLISTVIEW_ROOTVIEW);
		mClockListView.setAdapter(new ArrayAdapter<String>(this, 
														   android.R.layout.simple_list_item_1, 
														   android.R.id.text1, 
														   mItemClockStrings));
	}
	
	
}
