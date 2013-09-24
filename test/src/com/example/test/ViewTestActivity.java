package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewTestActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.test_view);
        
        TextView txtTextView = (TextView) findViewById(R.id.ID_TEST_VIEW_TEXT);
        
        ButtonViewTest buttonTest = (ButtonViewTest) findViewById(R.id.ID_TEST_VIEW_BUTTON);
    }
}
