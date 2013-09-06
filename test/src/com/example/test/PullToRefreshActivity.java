package com.example.test;

import com.ruoogle.base.pulltorefresh.PullToRefreshListView;

import android.R.anim;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class PullToRefreshActivity extends Activity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_pulltorefresh);
        
        PullToRefreshListView tmpPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.ID_TEST_PULLTOREFRESH_ROOT);
        ListView actListView = tmpPullToRefreshListView.getRefreshableView();
        actListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new String[]{"cc","bb"}));
    }
}
