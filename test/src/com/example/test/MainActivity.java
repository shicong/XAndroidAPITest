package com.example.test;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private String[] testItemsStrings = new String[]{
		"FrameLayoutNoticeBar≤‚ ‘",//0
		"ImageViewContour≤‚ ‘",//1
		"ImageViewUnderText≤‚ ‘",//2
		"ImageLoad≤‚ ‘",//3
		"GPS≤‚ ‘",//4
		"Audio Stream Media≤‚ ‘",//5
		"Scroller Wheel≤‚ ‘",//6
		"ExifInterface ≤‚ ‘",//7
		"Loaders≤‚ ‘",//8
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*test items*/
		final ListView sViewTestList = (ListView) findViewById(R.id.ID_TEST_LISTVIEW);
		sViewTestList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, testItemsStrings));
		
		sViewTestList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				Intent intent;
				switch (arg2) 
				{
					case 0:
						intent = new Intent(MainActivity.this, FrameLayoutNoticeBarActivity.class);
						startActivity(intent);
						break;
					case 4:
						intent = new Intent(MainActivity.this, GPSActivity.class);
						startActivity(intent);
						break;
					case 5:
						intent = new Intent(MainActivity.this, AudioStreamActivity.class);
						startActivity(intent);
						break;
					case 6:
						intent = new Intent(MainActivity.this, ScrollWheelActivity.class);
						startActivity(intent);
						break;
					case 7:
						intent = new Intent(MainActivity.this, ExifInterfaceTest.class);
						startActivity(intent);
						break;
					case 8:
						intent = new Intent(MainActivity.this, LoadersActivity.class);
						startActivity(intent);
						break;
					default:
						break;
				}
				
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
