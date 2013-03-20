/**
 * 
 */
package com.example.test;

import com.ruoogle.base.Util.Debug;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * @author shicong
 */
public class ScrollWheelActivity extends Activity {
	
	public static final String TAG = "ScrollWheelActivity";
	public static final boolean DEBUG = true;

	ScrollWheelView mScrollWheelView;
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_gamblingwheel);
		
		Bitmap b1 = BitmapFactory.decodeResource(getResources(), R.drawable.gambling_wheel_1);
		Bitmap b2 = BitmapFactory.decodeResource(getResources(), R.drawable.gambling_wheel_2);
		Bitmap b3 = BitmapFactory.decodeResource(getResources(), R.drawable.gambling_wheel_3);
		
		mScrollWheelView = new ScrollWheelView(this);
		mScrollWheelView.setBackgroundColor(0xFFFF0000);
		mScrollWheelView.setWheelBitmapArray(new Bitmap[]{b1,b2,b3});
		
		LinearLayout v = (LinearLayout) findViewById(R.id.ID_TEST_GAMBLINGWHEEL_ROOT);
		v.addView(mScrollWheelView);
		
		Button sBtn = (Button) findViewById(R.id.ID_TEST_GAMBLINGWHEEL_BTN);
		sBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mScrollWheelView.startWheel();
			}
		});
	}
}



class ScrollWheelView extends View{

	private Scroller mScroller;
	private Bitmap [] mGamblingBitmaps;
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ScrollWheelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public ScrollWheelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * @param context
	 */
	public ScrollWheelView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	/**
	 * 初始化
	 * @author shicong
	 *
	 * 2013-2-26
	 */
	private void init(Context context)
	{
		mScroller = new Scroller(context, new AccelerateDecelerateInterpolator(), false);
	}
	
	/* (non-Javadoc)
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Debug.d(ScrollWheelActivity.TAG, "",ScrollWheelActivity.DEBUG);
		
		canvas.drawBitmap(mGamblingBitmaps[0], new Matrix(), new Paint());
	}
	
	/* (non-Javadoc)
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		int width = 78;
		int height;
		
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = 78*3;
			if (heightMode == MeasureSpec.AT_MOST) {
				height = Math.min(height, heightSize);
			}
		}
		setMeasuredDimension(width, height);
	}
	
	/**
	 * 开始滚动
	 * @author shicong
	 *
	 * 2013-2-26
	 */
	public void startWheel()
	{
		mScroller.startScroll(0, 0, 0, 50, 5*1000);
	}
	
	/**
	 * 设置滚动需要的图片
	 * @author shicong
	 * 2013-2-26
	 */
	public void setWheelBitmapArray(Bitmap[] aBitmapArray) {
		// TODO Auto-generated method stub
		mGamblingBitmaps = aBitmapArray;
	}
}


