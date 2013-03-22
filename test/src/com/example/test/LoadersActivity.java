/**
 * 
 */
package com.example.test;

import android.annotation.TargetApi;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.SimpleCursorAdapter;

/**
 * @author shicong
 */
public class LoadersActivity extends ListActivity {

	private LoaderManager mLoaderManager;
	private Loader<Cursor> mLoader;
	private CursorAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.test_loaders);
		
		// Create an empty adapter we will use to display the loaded data.
		mAdapter = new SimpleCursorAdapter(
				this,
                android.R.layout.simple_list_item_2, null,
                new String[] {MediaStore.Images.Media.DATA, MediaStore.Images.Media.SIZE},
                new int[] { android.R.id.text1, android.R.id.text2 }, 0);
        setListAdapter(mAdapter);	
        
        //create loader
		mLoaderManager = getTestLoaderManager();
		mLoader = mLoaderManager.initLoader(0, null, mLoaderCallbacks);
		
		long threadid = Thread.currentThread().getId();
		Log.e("shicong", "onCreate Thread id = " + threadid);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        // Place an action bar item for searching.
        MenuItem item = menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView sv = new SearchView(this);
        sv.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				// TODO Auto-generated method stub
				return true;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				// TODO Auto-generated method stub
		        getLoaderManager().restartLoader(0, null, mLoaderCallbacks);
		        return true;				
			}
		});
        item.setActionView(sv);
		return super.onCreateOptionsMenu(menu);
	}




	LoaderCallbacks mLoaderCallbacks = new LoaderCallbacks<Cursor>() {

		@Override
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			// TODO Auto-generated method stub

			long threadid = Thread.currentThread().getId();
			Log.e("shicong", "onCreateLoader Thread id = " + threadid);
			
			String[] STORE_IMAGES = {MediaStore.Images.Media._ID,
									 MediaStore.Images.Media.DATA,
									 MediaStore.Images.Media.SIZE};
			CursorLoader sImageCursorLoader = new CursorLoader(LoadersActivity.this, 
															   MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 
															   STORE_IMAGES, 
															   null, 
															   null, 
															   null);
			return sImageCursorLoader;
		}

		@Override
		public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
			// TODO Auto-generated method stub
			long threadid = Thread.currentThread().getId();
			Log.e("shicong", "onLoadFinished Thread id = " + threadid);
			mAdapter.swapCursor(arg1);
		}

		@Override
		public void onLoaderReset(Loader<Cursor> arg0) {
			// TODO Auto-generated method stub
			long threadid = Thread.currentThread().getId();
			Log.e("shicong", "onLoaderReset Thread id = " + threadid);
			mAdapter.swapCursor(null);
		}
	};
	
	
	@TargetApi(11)
	private LoaderManager getTestLoaderManager()
	{
		return getLoaderManager();
	}
	
	
}
