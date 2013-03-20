/**
 * 
 */
package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author shicong
 */
public class GPSActivity extends Activity {

	private String TraceString = "��ʼ����\n";
	private static final String TAG = "GPSActivity";
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_gps);

        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPSģ������", Toast.LENGTH_SHORT).show();
            getLocation();
            return;
        }

        Toast.makeText(this, "�뿪��GPS��", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent,0); //��Ϊ������ɺ󷵻ص���ȡ����
	}
	
	/**
	 * 
	 * @author shicong
	 *
	 * 2013-2-22
	 */
	private void getLocation()
    {
        // ��ȡλ�ù������
        LocationManager locationManager;
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        
        // ���ҵ�������Ϣ
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���

        String provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
        
        /*��ӡ���е�Providers*/
        Log.e(TAG, locationManager.getProviders(criteria, true)+"");
        
        Log.e(TAG, "provider = " + provider);
        Location location = locationManager.getLastKnownLocation(provider); // ͨ��GPS��ȡλ��
        updateToNewLocation(location);
        
        // ���ü��������Զ����µ���Сʱ��Ϊ���N��(1��Ϊ1*1000������д��ҪΪ�˷���)����Сλ�Ʊ仯����N��
        locationManager.requestLocationUpdates(provider, 10 * 1000, 5,new LocationListener() {
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				Log.e(TAG, "onStatusChanged " + provider + "/" + status );
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-onProviderEnabled method stub
				Log.e(TAG, "onLocationChanged " + provider);
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				Log.e(TAG, "onProviderDisabled " + provider);
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				Log.e(TAG, "onLocationChanged");
				updateToNewLocation(location);
			}
		});
    }	
	
	/**
	 * 
	 * @author shicong
	 *
	 * 2013-2-22
	 * @param location
	 */
	private void updateToNewLocation(Location location) {

        TextView tv1 = (TextView) this.findViewById(R.id.ID_TEST_GPS_TEXTVIEW);
        if (location != null) {
            double  latitude = location.getLatitude();
            double longitude= location.getLongitude();
            TraceString = TraceString + "\n" + "ά�ȣ�" +  latitude+ " \n����" + longitude + "\n";
            tv1.setText(TraceString);
        } else {
            tv1.setText("�޷���ȡ������Ϣ");
        }
    }
	
}
