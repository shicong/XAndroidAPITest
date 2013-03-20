/**
 * 
 */
package com.example.test;

import java.io.IOException;

import com.ruoogle.base.BaseAudioPlayer;
import com.ruoogle.base.Util.Debug;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author shicong
 */
public class AudioStreamActivity extends Activity {

	private static final String TAG = "AudioStreamActivity";
	private static final boolean DEBUG = true;
	private MediaPlayer mMediaPlayer;
	private String mURL = "http://172.16.10.13:7070/audio";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_audiostream);
		
		
		//
		TextView sTextView = (TextView) findViewById(R.id.ID_TEST_AUDIOSTREAM_TEXTVIEW);
		sTextView.setText(mURL);
		
		//
		Button  sButton = (Button) findViewById(R.id.ID_TEST_AUDIOSTREAM_START);
		sButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				mMediaPlayer = new MediaPlayer();
				mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				
				/*设置数据准备就绪后的回调函数*/
				mMediaPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						// TODO Auto-generated method stub
						Debug.d(TAG, "onPrepared to start play", DEBUG);
						mp.start();
					}
				});
				
				/*设置结束后的回调函数*/
				mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						// TODO Auto-generated method stub
						Debug.d(TAG, "onCompletion play", DEBUG);
						
						mp.stop();
						mp.release();
						mMediaPlayer = null;
					}
				});
				
				try {
					mMediaPlayer.setDataSource(AudioStreamActivity.this,Uri.parse(mURL));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mMediaPlayer.prepareAsync();
			}
		});
	}
	
}
