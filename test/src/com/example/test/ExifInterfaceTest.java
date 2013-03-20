/**
 * 
 */
package com.example.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.ruoogle.base.Util.UtilSystem;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author shicong
 */
public class ExifInterfaceTest extends Activity {

	List<String> mPicURLList = new ArrayList<String>();
	
	Bitmap mBmPic;
	BitmapDrawable mBmPicThumbNail;
	
	TextView mViewURLs,mViewCurrURL;
	ImageView mViewThumbNail,mViewPic;
	Button mViewBtnPre,mViewBtnNext;
	
	int mCurrURLIndex = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_exifinterface);
		
		mViewURLs = (TextView) findViewById(R.id.ID_TEST_EXIFINTERFACE_PIC_URLS);
		mViewCurrURL = (TextView) findViewById(R.id.ID_TEST_EXIFINTERFACE_PIC_CURR_URL);
		
		mViewBtnPre = (Button) findViewById(R.id.ID_TEST_EXIFINTERFACE_BTN_PRE);
		mViewBtnNext = (Button) findViewById(R.id.ID_TEST_EXIFINTERFACE_BTN_NEXT);
		
		mViewThumbNail = (ImageView) findViewById(R.id.ID_TEST_EXIFINTERFACE_PIC_THUMBNAIL);
		mViewPic = (ImageView) findViewById(R.id.ID_TEST_EXIFINTERFACE_PIC);
		
		mPicURLList = getInitImageURLList();
		if((mPicURLList == null) || (mPicURLList.size() == 0))
		{
			mViewCurrURL.setText("系统没有图片！~~~~~~~~~~~~~~~~~~");
			return;
		}
		
		StringBuilder sURLS = new StringBuilder();
		for (int i = 0; i < mPicURLList.size(); i++) {
			sURLS.append(mPicURLList.get(i));
			sURLS.append("\n");
		}
		
		mViewURLs.setText(sURLS.toString());
		mViewCurrURL.setText(mPicURLList.get(0));
		mCurrURLIndex = 0;
		
		ShowThePic(mPicURLList.get(mCurrURLIndex));
		
		mViewBtnPre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mCurrURLIndex == 0)
				{
					UtilSystem.SystemToast(ExifInterfaceTest.this, "已经是第一张");
					return;
				}
				mCurrURLIndex--;
				mViewCurrURL.setText(mPicURLList.get(mCurrURLIndex));
				ShowThePic(mPicURLList.get(mCurrURLIndex));
			}
		});
		
		
		mViewBtnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(mCurrURLIndex == mPicURLList.size())
				{
					UtilSystem.SystemToast(ExifInterfaceTest.this, "已经是最后一张");
					return;
				}
				mCurrURLIndex++;
				mViewCurrURL.setText(mPicURLList.get(mCurrURLIndex));
				ShowThePic(mPicURLList.get(mCurrURLIndex));
			}
		});
		
		
	}
	
	
	private void ShowThePic(String aURL)
	{
		try {
			ExifInterface exifInterface = new ExifInterface(aURL);
			
			if(mBmPic != null)
			{
				mBmPic.recycle();
			}
			mBmPic = BitmapFactory.decodeFile(aURL, UtilSystem.getBitmapDecodeOpts(new File(aURL), 300, 300));
			mViewPic.setImageBitmap(mBmPic);
			
			if(mBmPicThumbNail != null)
			{
				mBmPicThumbNail.getBitmap().recycle();
			}
			
			byte ThumbnailArray[] = exifInterface.getThumbnail();
			if(ThumbnailArray != null)
			{
				InputStream aInputStream = new ByteArrayInputStream(ThumbnailArray);
				mBmPicThumbNail = new BitmapDrawable(aInputStream);
				mViewThumbNail.setImageDrawable(mBmPicThumbNail);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 初始化列表数据,如果没有初始化数据，则返回NUll
	 * @return
	 */
	private List<String> getInitImageURLList()
	{
        String[] STORE_IMAGES = {MediaStore.Images.Media.DATA};
        List<String> tmpImageURLList = new ArrayList<String>();    
        
		Cursor aCursor =  getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,STORE_IMAGES,null, null, null);
		aCursor.moveToFirst();
		
		for (int i = 0; i < aCursor.getCount(); i++) 
		{
			String data = aCursor.getString(0);
			tmpImageURLList.add(data);
			
			aCursor.moveToNext();
		}
		
		return tmpImageURLList;
	}
	
}
