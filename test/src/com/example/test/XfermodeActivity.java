/**
 * 
 */
package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

/**
 * @author shicong
 */
public class XfermodeActivity extends Activity {

	ViewGroup mViewRootView;
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_xfermode);
		
		mViewRootView = (ViewGroup) findViewById(R.id.ID_TEST_XFERMODE_ROOTVIEW);
		
		ImageViewExt sImageViewExt = new ImageViewExt(this);
		sImageViewExt.setScaleType(ScaleType.CENTER_INSIDE);
		sImageViewExt.setBackgroundColor(Color.BLUE);
		sImageViewExt.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
		
		mViewRootView.addView(sImageViewExt);
		
	}
	
	
	private class ImageViewExt extends ImageView{
		
		Paint mPaint;
		Canvas mCanvas;
		
		/**
		 * @param context
		 * @param attrs
		 * @param defStyle
		 */
		public ImageViewExt(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init();
		}

		/**
		 * @param context
		 * @param attrs
		 */
		public ImageViewExt(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}

		/**
		 * @param context
		 */
		public ImageViewExt(Context context) {
			super(context);
			init();
		}

		private void init()
		{
			mPaint = new Paint();
			mPaint.setStyle(Paint.Style.FILL);
			mPaint.setColor(Color.RED);
			
			mCanvas = new Canvas();
		}
		
		
		
		/* (non-Javadoc)
		 * @see android.widget.ImageView#onDraw(android.graphics.Canvas)
		 */
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub

			int x = canvas.saveLayer(0, 0, 300, 300, null, Canvas.ALL_SAVE_FLAG);
			
			/**********************************************/			
			canvas.drawBitmap(makeDst(300, 300), 0, 0, mPaint);

			
			Xfermode a = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
			mPaint.setXfermode(a);
			canvas.drawBitmap(makeSrc(300, 300), 0, 0, mPaint);
			/**********************************************/
			
			canvas.restoreToCount(x);
		}
		
		/**
		 * create a bitmap with a rect, used for the "src" image
		 * @author shicong
		 *
		 * 2013-3-22
		 * @param w
		 * @param h
		 * @return
		 */
		private Bitmap makeSrc(int w, int h) {
	        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
	        Canvas c = new Canvas(bm);
	        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
	        
	        p.setColor(Color.RED);
	        c.drawRect(0, 0, w/2, h/2, p);
	        return bm;
	    }

		/**
		 * create a bitmap with a circle, used for the "dst" image
		 * @author shicong
		 *
		 * 2013-3-22
		 * @param w
		 * @param h
		 * @return
		 */
	   private Bitmap makeDst(int w, int h) {
			Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
	        Canvas c = new Canvas(bm);

	        Bitmap tmpbm = BitmapFactory.decodeResource(getResources(), R.drawable.comm_btn_bg_more);
	        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
	        
//	        p.setColor(0xFFFFCC44);
	        c.drawBitmap(tmpbm, 40, 40, p);
//	        c.drawOval(new RectF(0, 0, w*3/4, h*3/4), p);
	        return bm;
	    }
	}
}
