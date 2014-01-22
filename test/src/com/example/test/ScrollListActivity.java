package com.example.test;

import java.util.ArrayList;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;

public class ScrollListActivity extends Activity
{
    Vibrator mVibrator;
    
    PopupWindow mPopupWindow;
    scrollList mScrollList;
    ScrollView mScrollView;
    int mOffsetX = 0;
    int mOffsetY = 60;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        String[] labelStrings = new String[]{"表情图","表情符","颜文字","鲸鱼表情","+","-","0","1","2"}; 
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_scrollview);
        View rootView = findViewById(R.id.ID_TEST_SCROLLVIEW_ROOT);
        
        
        Bitmap tmpBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clock_face);
//        mScrollView = (ScrollView) findViewById(R.id.ID_TEST_SCROLLVIEW_S1);
        mScrollList = new scrollList(this);
        ArrayList<ListItem> listItems = new ArrayList<ScrollListActivity.ListItem>();
        for (int i = 0; i < labelStrings.length; i++)
        {
            ListItem tmpItem = new ListItem();
            tmpItem.lable = labelStrings[i];
            tmpItem.backGroundBitmap = tmpBitmap;
            listItems.add(tmpItem);
        }
        
        //初始化弹出框
        ImageView tmpImageView = new ImageView(this);
        tmpImageView.setBackgroundColor(Color.GRAY);
        tmpImageView.setImageResource(R.drawable.clock_face);
        tmpImageView.setLayoutParams(new ViewGroup.LayoutParams(40, 40));
        mPopupWindow = new PopupWindow(tmpImageView, 40, 40, false);
        mPopupWindow.setContentView(tmpImageView);
        
        mScrollList.setListItem(listItems , 100, 100);
        mScrollView.addView(mScrollList);
        mVibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
        
        
        mScrollView.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("shicong", "scroll scroll scroll down event...");
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.e("shicong", "scroll scroll scroll up event...");
                        if (mPopupWindow.isShowing())
                        {
                            mPopupWindow.dismiss();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.e("shicong", "scroll scroll scroll move event...");
                        if (mPopupWindow.isShowing())
                        {
                            mOffsetY++;
                            Log.e("shicong", "scroll scroll scroll offsetxy" + mOffsetX + "/" + mOffsetY);
                            mPopupWindow.update(mScrollView, mOffsetX, mOffsetY, -1, -1);

                            int x = mScrollView.getScrollX();
                            int y = mScrollView.getScrollY();
                            Log.e("shicong", "scroll scroll scroll offsetxy xy" + x + "/" + y);
                            
                            return true;
                        }else {
                            int x = mScrollView.getScrollX();
                            int y = mScrollView.getScrollY();
                            Log.e("shicong", "scroll scroll scroll xy" + x + "/" + y);
                            return false;
                        }
                    default:
                        break;
                }
                return false;
            }
        });
        
        
        mScrollList.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.e("shicong", "list list list onClick event...");
                if (mPopupWindow.isShowing())
                {
                    mPopupWindow.dismiss();
                }else {
                    v.setBackgroundColor(Color.RED);
                }
            }
        });

        mScrollList.setOnLongClickListener(new OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                Log.e("shicong", "list list list onLongClick event...");
                mVibrator.vibrate(1000);
                v.setBackgroundColor(Color.RED);
                mPopupWindow.showAsDropDown(mScrollView,mOffsetX, mOffsetY);
                return false;
            }
        });
    }
        
    
    private class ListItem{
        String lable;
        Bitmap backGroundBitmap;
    }
    private class scrollList extends View{

        Paint mPaint;
        public scrollList(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
            init();
        }

        public scrollList(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            init();
        }

        public scrollList(Context context)
        {
            super(context);
            init();
        }
        
        int mBackGroundColor = Color.GRAY;
        int mForeGroundColor = Color.WHITE;
        ArrayList<ListItem> mListItems;
        int mItemWidth = 100;
        int mItemHeight = 100;
        float mFontSize = 25;
        private void init()
        {
            mPaint = new Paint();  
            mPaint.setColor(mForeGroundColor);
            mPaint.setTextSize(mFontSize);
            setBackgroundColor(mBackGroundColor);
        }
        
        public void setColors(int backGroundColor,int foreGroundColor)
        {
            mBackGroundColor = backGroundColor;
            mForeGroundColor = foreGroundColor;
        }
        
        public void setListItem(ArrayList<ListItem> listItems,int itemWidth,int itemHeight)
        {
            mListItems = listItems;
            mItemHeight = itemHeight;
            mItemWidth = itemWidth;
        }
        
        @Override
        protected void onDraw(Canvas canvas)
        {
            // TODO Auto-generated method stub
            
            if (mListItems == null)
            {
                return;
            }
            
            for (int i = 0; i < mListItems.size(); i++)
            {
                ListItem tmpItem = mListItems.get(i);
                
                canvas.drawBitmap(tmpItem.backGroundBitmap, (mItemWidth - tmpItem.backGroundBitmap.getWidth())/2, i * mItemHeight + (mItemHeight - tmpItem.backGroundBitmap.getHeight())/2, mPaint);
                canvas.drawLine(0, (i + 1)  * mItemHeight, mItemWidth, (i + 1) * mItemHeight, mPaint);
            }
        }
        
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            // TODO Auto-generated method stub
            if ((mListItems == null) || (mItemHeight == 0))
            {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
                return;
            }
            setMeasuredDimension(mItemWidth, mItemHeight * mListItems.size());
            Log.e("shicong", "onMeasure ....");
        }
    }
    
}
