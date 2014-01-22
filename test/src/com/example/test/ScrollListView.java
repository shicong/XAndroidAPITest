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

    private PopupWindow mPopupWindow;//������
    private scrollList mScrollList;//�����б�
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
     * ��ʼ��
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
        
        //����ScrollView���������
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
     * ��ʼ���б���������
     * @param bkColor �б�ı���ɫ
     * @param foreColor �б��ǰ��ɫ���������֣�
     * @param items Item�ϵ�ͼƬ
     * @param itemWidth Item�Ŀ��
     * @param itemHeight Item�ĸ߶�
     * @author shicong
     */
    public void setOnListInit(int bkColor,int foreColor,ArrayList<Bitmap> items,int itemWidth,int itemHeight)
    {
        mScrollList.setColors(bkColor, foreColor);
        mScrollList.setListItem(items, itemWidth, itemHeight);
    }
    
    /**
     * ���õ�����Ŀ��
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
     * ���õ������í��View�Լ�ƫ��λ��
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
     * �����б�
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
         * ��ʼ��
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
         * ���ñ���ɫ��ǰ��ɫ
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
         * ����List���������
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
