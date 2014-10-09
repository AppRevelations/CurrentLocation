package com.currentlatlong;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

public class MainActivity extends Activity implements LocationListener{

	TextView tvloc;
	String latLong = "No Location Found!!!";
	LocationManager lManager;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.activity_main);
	        tvloc= (TextView) findViewById(R.id.textview1);
	        lManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
	       // locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,
	       //     500.0f, locationListener);

	        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L,
		            500.0f, this);
	        Location loc = lManager
	                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
	       // Location location = locManager
	         //       .getLastKnownLocation(LocationManager.GPS_PROVIDER);
	        if (loc != null) {
	            String lat = String.valueOf(loc.getLatitude());
	            String lng = String.valueOf(loc.getLongitude());
	            latLong = "Latitude:" + lat + "\nLongitude:" + lng;
	            tvloc.setText("Your Current Position is:\n" + latLong);
	        }
	    }

		public void showDialog(String provider) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					MainActivity.this);

			alertDialog.setTitle(provider + " SETTINGS");

			alertDialog
					.setMessage(provider + "LOCATION is not enabled! Turn it ON?");

			alertDialog.setPositiveButton("Settings",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							MainActivity.this.startActivity(intent);
						}
					});

			alertDialog.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});

			alertDialog.show();
		}
	    		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
	    			
	    	        if (location != null) {
	    	            double lat = location.getLatitude();
	    	            double lng = location.getLongitude();
	    	            latLong = "Latitude:" + lat + "\nLongitude:" + lng;
	    	        } else {
	    	            latLong = "No location found";
	    	            //use "GPS" for gps positioning and "Network" otherwise
	    	            showDialog("NETWORK");
	    	        }
	    	        tvloc.setText("Your Current Position is:\n" + latLong);
	    			
	    			
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
			showDialog("NETWORK");
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
}