/**
 * 
 */
package com.example.test.clocklistview;

import com.example.test.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;


/**
 * @author shicong
 */
public class ClockListView extends ListView implements OnScrollListener{

	private int mClockResID;
	private View mViewClockView;
	private OnScrollListener mOnScrollListener;
	private OnPositionChangedListener mPositionChangedListener;
	private int mScrollBarPanelPosition = 0;
    /*
     * keep track of Measure Spec
     */
    private int mWidthMeasureSpec;
    private int mHeightMeasureSpec;
    
	
    public static interface OnPositionChangedListener {
        public void onPositionChanged(ClockListView listView, int position, View scrollBarPanel);
        
        public void onScollPositionChanged(View scrollBarPanel,int top);
    }
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ClockListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray  typeArray = context.obtainStyledAttributes(attrs,R.styleable.ClockListView);
		if(typeArray.hasValue(R.styleable.ClockListView_scrollBarPanel))
		{
			mClockResID = typeArray.getResourceId(R.styleable.ClockListView_scrollBarPanel, -1);
			mViewClockView = LayoutInflater.from(getContext()).inflate(mClockResID, this, false);
		}
		
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ClockListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray  typeArray = context.obtainStyledAttributes(attrs,R.styleable.ClockListView);
		if(typeArray.hasValue(R.styleable.ClockListView_scrollBarPanel))
		{
			mClockResID = typeArray.getResourceId(R.styleable.ClockListView_scrollBarPanel, -1);
			mViewClockView = LayoutInflater.from(getContext()).inflate(mClockResID, this, false);
		}
	}

	/**
	 * @param context
	 */
	public ClockListView(Context context) {
		super(context);
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AbsListView#setOnScrollListener(android.widget.AbsListView.OnScrollListener)
	 */
	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mOnScrollListener = l;
	}
	
	/* (non-Javadoc)
	 * @see android.widget.AbsListView#onLayout(boolean, int, int, int, int)
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		
        if (mViewClockView != null) {
            final int x = getMeasuredWidth() - mViewClockView.getMeasuredWidth() - getVerticalScrollbarWidth();
            mViewClockView.layout(x, mScrollBarPanelPosition,x 
            		+ mViewClockView.getMeasuredWidth(), mScrollBarPanelPosition
                            + mViewClockView.getMeasuredHeight());
        }
		
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ListView#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidthMeasureSpec = widthMeasureSpec;
        mHeightMeasureSpec = heightMeasureSpec;
		
		if(mClockResID > 0)
		{
			measureChild(mViewClockView, widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	/* (non-Javadoc)
	 * @see android.widget.ListView#dispatchDraw(android.graphics.Canvas)
	 */
	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.dispatchDraw(canvas);

		if (mViewClockView != null && mViewClockView.getVisibility() == View.VISIBLE) {

            drawChild(canvas, mViewClockView, getDrawingTime());
        }
		
	}

	/* (non-Javadoc)
	 * @see android.widget.AbsListView.OnScrollListener#onScroll(android.widget.AbsListView, int, int, int)
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,int visibleItemCount, int totalItemCount) {
        if (null != mPositionChangedListener && (mViewClockView != null)) {

            // Don't do anything if there is no itemviews
            if (totalItemCount > 0) {
                /*
                 * from android source code (ScrollBarDrawable.java)
                 */
                final int thickness = getVerticalScrollbarWidth();
                int height = Math.round((float) getMeasuredHeight() * computeVerticalScrollExtent()
                        / computeVerticalScrollRange());
                int thumbOffset = Math.round((float) (getMeasuredHeight() - height)
                        * computeVerticalScrollOffset()
                        / (computeVerticalScrollRange() - computeVerticalScrollExtent()));
                final int minLength = thickness * 2;
                if (height < minLength) {
                    height = minLength;
                }
                thumbOffset += height / 2;

                /*
                 * find out which itemviews the center of thumb is on
                 */
                final int count = getChildCount();
                for (int i = 0; i < count; ++i) {
                    final View childView = getChildAt(i);
                    if (childView != null) {
                        if (thumbOffset > childView.getTop() && thumbOffset < childView.getBottom()) {
                            /*
                             * we have our candidate
                             */
                        	int mLastPosition = -1;
                            if (mLastPosition != firstVisibleItem + i) {
                                mLastPosition = firstVisibleItem + i;

                                /*
                                 * inform the position of the panel has changed
                                 */
                                mPositionChangedListener.onPositionChanged(this, mLastPosition,
                                		mViewClockView);

                                /*
                                 * measure panel right now since it has just
                                 * changed INFO: quick hack to handle TextView
                                 * has ScrollBarPanel (to wrap text in case
                                 * TextView's content has changed)
                                 */
                                measureChild(mViewClockView, mWidthMeasureSpec, mHeightMeasureSpec);
                            }
                            break;
                        }
                    }
                }

                /*
                 * update panel position
                 */
                mScrollBarPanelPosition = thumbOffset - mViewClockView.getMeasuredHeight() / 2;
                final int x = getMeasuredWidth() - mViewClockView.getMeasuredWidth()
                        - getVerticalScrollbarWidth();
                System.out.println("left==" + x + " top==" + mScrollBarPanelPosition + " bottom=="
                        + (x + mViewClockView.getMeasuredWidth()) + " right=="
                        + (mScrollBarPanelPosition + mViewClockView.getMeasuredHeight()));
                mViewClockView.layout(x, mScrollBarPanelPosition,
                        x + mViewClockView.getMeasuredWidth(), mScrollBarPanelPosition
                                + mViewClockView.getMeasuredHeight());
                mPositionChangedListener.onScollPositionChanged(this, mScrollBarPanelPosition);
            }
        }		
		
		
		if(mOnScrollListener != null){
			mOnScrollListener.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
		}
	}

	/* (non-Javadoc)
	 * @see android.widget.AbsListView.OnScrollListener#onScrollStateChanged(android.widget.AbsListView, int)
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		
		
		
		if(mOnScrollListener != null){
			mOnScrollListener.onScrollStateChanged(view, scrollState);
		}
	}
}
