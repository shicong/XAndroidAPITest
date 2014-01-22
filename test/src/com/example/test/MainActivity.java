package com.example.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.test.clocklistview.ClockListViewActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
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
		"xFerModer≤‚ ‘",//9
		"ClockListView≤‚ ‘",//10
		"∆Ù∂Ø±µƒApp≤‚ ‘",//11
		"œ¬¿¥À¢–¬≤‚ ‘",//12
		"View test",//13
		"πˆ∂Ø¡–±Ì",//14
		"πˆ∂Ø¡–±ÌLinear",//15
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		/*test java*/
		JavaTest.testJava();
		
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
					case 9:
						intent = new Intent(MainActivity.this, XfermodeActivity.class);
						startActivity(intent);
						break;
					case 10:
						intent = new Intent(MainActivity.this, ClockListViewActivity.class);
						startActivity(intent);
						break;
					case 11:
						intent = new Intent(MainActivity.this, StartOtherAppTest.class);
						startActivity(intent);
						break;
					case 12:
                        intent = new Intent(MainActivity.this, PullToRefreshActivity.class);
                        startActivity(intent);
					    break;
					case 13:
                        intent = new Intent(MainActivity.this, ViewTestActivity.class);
                        startActivity(intent);
                        break;
					case 14:
                        intent = new Intent(MainActivity.this, ScrollListActivity.class);
                        startActivity(intent);
					    break;
					case 15:    
                        intent = new Intent(MainActivity.this, ScrollListLinearActivity.class);
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
