/**
 * 
 */
package com.example.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

/**
 * @author shicong
 */
public class LoadersActivity extends Activity {

	private LoaderManager mLoaderManager;
	private Loader<Cursor> mLoader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_loaders);
		
		mLoaderManager = getTestLoaderManager();
		mLoader = mLoaderManager.initLoader(0, null, mLoaderCallbacks);
	}
	
	LoaderCallbacks mLoaderCallbacks = new LoaderCallbacks<Cursor>() {

		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLoaderReset(Loader<Cursor> arg0) {
			// TODO Auto-generated method stub
			
		}
	};
	
	
	@TargetApi(11)
	private LoaderManager getTestLoaderManager()
	{
		return getLoaderManager();
	}
	
	
}
