package com.example.test;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;


public class ScrollListView extends ScrollView
{

    private PopupWindow mPopupWindow;//弹出框
    private scrollList mScrollList;//滚动列表
    private int mOffsetX = 0;
    private int mOffsetY = 0;
    private Context mContext;
    private View mAnchorView;
    
    public ScrollListView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initView(context);
    }

    public ScrollListView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initView(context);
    }

    public ScrollListView(Context context)
    {
        super(context);
        initView(context);
    }
    
    /**
     * 初始化
     * @param context
     * @author shicong
     */
    private void initView(Context context)
    {
        mContext = context;
        mAnchorView = this;
        mScrollList = new scrollList(context);
        mScrollList.setColors(Color.GRAY, Color.WHITE);
        mScrollList.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
                if (mPopupWindow == null)
                {
                    return false;
                }
                Vibrator mVibrator = (Vibrator) mContext.getSystemService(Context.VIBRATOR_SERVICE );
                mVibrator.vibrate(1000);
                v.setBackgroundColor(Color.RED);
                Log.e("shicong", "x/y = " + mOffsetX + "/" + mOffsetY);
                mPopupWindow.showAtLocation(mAnchorView,Gravity.LEFT | Gravity.BOTTOM,mOffsetX, mOffsetY);
                return false;
            }
        });
        
        //设置ScrollView的相关属性
        setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        addView(mScrollList);
        setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_UP:
                        if (mPopupWindow.isShowing())
                        {
                            mPopupWindow.dismiss();
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mPopupWindow.isShowing())
                        {
                            mOffsetY--;
                            Log.e("shicong", "xxxxxxxxxx/yyyyyyyy = " + mOffsetX + "/" + mOffsetY);
                            mPopupWindow.update(mOffsetX, mOffsetY, -1, -1);
                            return true;
                        }else {
                            return false;
                        }
                    default:
                        break;
                }
                return false;
            }
        });
        
    }

    /**
     * 初始化列表的相关属性
     * @param bkColor 列表的背景色
     * @param foreColor 列表的前景色（例如文字）
     * @param items Item上的图片
     * @param itemWidth Item的宽度
     * @param itemHeight Item的高度
     * @author shicong
     */
    public void setOnListInit(int bkColor,int foreColor,ArrayList<Bitmap> items,int itemWidth,int itemHeight)
    {
        mScrollList.setColors(bkColor, foreColor);
        mScrollList.setListItem(items, itemWidth, itemHeight);
    }
    
    /**
     * 设置弹出框的宽高
     * @param width
     * @param height
     * @author shicong
     */
    public void setOnPopWindowInit(Bitmap bm,int bkColor,int width,int height)
    {
        ImageView tmpImageView = new ImageView(mContext);
        tmpImageView.setBackgroundColor(bkColor);
        tmpImageView.setImageBitmap(bm);
        tmpImageView.setLayoutParams(new ViewGroup.LayoutParams(width, height));
        mPopupWindow = new PopupWindow(tmpImageView, width, height, false);
        mPopupWindow.setContentView(tmpImageView);
    }
    
    /**
     * 设置弹出框的铆钉View以及偏移位置
     * @param anchor
     * @param offsetX
     * @param offsetY
     * @author shicong
     */
    public void setPopWindownAnchorView(View anchor,int offsetX,int offsetY)
    {
        mAnchorView = anchor;
        mOffsetX = offsetX;
        mOffsetY = offsetY;
    }
    
    /**
     * 滚动列表
     * @author shicong
     * @2013-11-4
     */
    private class scrollList extends View{

        private int mBackGroundColor = Color.GRAY;
        private int mForeGroundColor = Color.WHITE;
        private ArrayList<Bitmap> mListItems;
        private int mItemWidth = 0;
        private int mItemHeight = 0;
        private Paint mPaint;
        public scrollList(Context context, AttributeSet attrs, int defStyle)
        {
            super(context, attrs, defStyle);
            initView(context);
        }

        public scrollList(Context context, AttributeSet attrs)
        {
            super(context, attrs);
            initView(context);
        }

        public scrollList(Context context)
        {
            super(context);
            initView(context);
        }
        
        /**
         * 初始化
         * @param context
         * @author shicong
         */
        private void initView(Context context)
        {
            mPaint = new Paint();  
            mPaint.setColor(mForeGroundColor);
            setBackgroundColor(mBackGroundColor);
        }
        
        /**
         * 设置背景色和前景色
         * @param backGroundColor
         * @param foreGroundColor
         * @author shicong
         */
        public void setColors(int backGroundColor,int foreGroundColor)
        {
            mBackGroundColor = backGroundColor;
            mForeGroundColor = foreGroundColor;
        }
        
        /**
         * 设置List的相关属性
         * @param listItems
         * @param itemWidth
         * @param itemHeight
         * @author shicong
         */
        public void setListItem(ArrayList<Bitmap> listItems,int itemWidth,int itemHeight)
        {
            mListItems = listItems;
            mItemHeight = itemHeight;
            mItemWidth = itemWidth;
        }
        
        @Override
        protected void onDraw(Canvas canvas)
        {
            if (mListItems == null)
            {
                return;
            }
            
            for (int i = 0; i < mListItems.size(); i++)
            {
                Bitmap tmpItem = mListItems.get(i);
                canvas.drawBitmap(tmpItem, (mItemWidth - tmpItem.getWidth())/2, i * mItemHeight + (mItemHeight - tmpItem.getHeight())/2, mPaint);
                canvas.drawLine(0, (i + 1)  * mItemHeight, mItemWidth, (i + 1) * mItemHeight, mPaint);
            }
        }
        
        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
        {
            if ((mListItems == null) || (mItemHeight == 0))
            {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);  
                return;
            }
            setMeasuredDimension(mItemWidth, mItemHeight * mListItems.size());
        }
    }
}
