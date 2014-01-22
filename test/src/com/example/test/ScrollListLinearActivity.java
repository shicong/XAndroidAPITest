package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class ScrollListLinearActivity extends Activity
{
    ScrollListView mScrollListView;
    ArrayList<Bitmap> mArrayList = new ArrayList<Bitmap>();
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_scrollview);
        LinearLayout rootView = (LinearLayout) findViewById(R.id.ID_TEST_SCROLLVIEW_ROOT);
        
        
        
        /********************************************************************/
        mScrollListView = new ScrollListView(this);
        Bitmap tmpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clock_face);
        for (int i = 0; i < 20; i++)
        {
            mArrayList.add(tmpBitmap);
        } 
        mScrollListView.setOnListInit(Color.GRAY, Color.WHITE, mArrayList, 100, 100);
        mScrollListView.setOnPopWindowInit(tmpBitmap, Color.GREEN, 50, 50);
        mScrollListView.setPopWindownAnchorView(mScrollListView, 0, 0);
        /********************************************************************/
        
        
        
        
        
        rootView.addView(mScrollListView);
    }
    
}
