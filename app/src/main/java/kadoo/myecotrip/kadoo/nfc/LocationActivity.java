package kadoo.myecotrip.kadoo.nfc;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import kadoo.myecotrip.kadoo.PermissionImpl;
import kadoo.myecotrip.kadoo.R;
import zinka.com.permissionlib.PermissionFragment;


public class LocationActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    public PermissionFragment mPermissionFragment;

    private static final String HEADLESS_FRAGMENT = "head_less_fragment";
    private static final String PROGRESS_FRAGMENT = "progress_fragment";
    // LogCat tag
    private static final String TAG = LocationActivity.class.getSimpleName();

    private final static int PLAY_SERVICES_REQUEST = 1000;
    private final static int REQUEST_CHECK_SETTINGS = 2000;

    private Location mLastLocation;
    boolean isPermissionGranted;

    // Google client to interact with Google API

    private GoogleApiClient mGoogleApiClient;

   public static double latitude;
    public static double longitude;
    TextView tvAddress;
    TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initPermission();
        checkPermissions();
        tvEmpty = (TextView) findViewById(R.id.tvEmpty);
        tvAddress = (TextView) findViewById(R.id.tvAddress);
        buildGoogleApiClient();
        findViewById(R.id.rlPickLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLocation();

                if (mLastLocation != null) {
                    latitude = mLastLocation.getLatitude();
                    longitude = mLastLocation.getLongitude();
                    getAddress();

                } else {

                    /*if(btnProceed.isEnabled())
                        btnProceed.setEnabled(false);*/

                    showToast("Couldn't get the location. Make sure location is enabled on the device");
                }
            }
        });
        findViewById(R.id.btnLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // showToast("Proceed to the next step");
                //TODO:Here call next activity
                start(KadooTagNFCWrite.class);
            }
        });
        // check availability of play services
        if (checkPlayServices()) {

            // Building the GoogleApi client
            buildGoogleApiClient();
        }
    }
    private void start(Class<? extends Activity> token) {
        Intent intent = new Intent(this, token);
        startActivity(intent);
    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void getLocation() {
        if (isPermissionGranted) {

            try {
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            } catch (SecurityException e) {
                e.printStackTrace();
            }

        }
    }

    private void initPermission() {
        mPermissionFragment = (PermissionFragment) getSupportFragmentManager().findFragmentByTag(HEADLESS_FRAGMENT);
        if (mPermissionFragment == null) ;
        {
            mPermissionFragment = new PermissionFragment();
            getSupportFragmentManager().beginTransaction().add(mPermissionFragment, HEADLESS_FRAGMENT).commitNow();
        }
    }

    private void checkPermissions() {
        if (mPermissionFragment.isPermissionGRanted(Manifest.permission.ACCESS_FINE_LOCATION) ||
                mPermissionFragment.isPermissionGRanted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            isPermissionGranted = true;
            return;
        }

        mPermissionFragment.requestForPermission(
                new PermissionImpl() {
                    @Override
                    public void permissionApproved(String... permissions) {
                        if (mPermissionFragment.isPermissionGRanted(Manifest.permission.ACCESS_FINE_LOCATION)) {
                            //gotoNext();
                        } else if (mPermissionFragment.isPermissionGRanted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                            //gotoNext();
                        } else {
                            displayPermissionDenied();
                        }
                    }

                    @Override
                    public void onApproved(String approved) {

                    }
                }, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);
    }


    private void displayPermissionDenied() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("enable_permission");

        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        checkPermissions();
                    }
                });
        builder1.setNegativeButton(
                "NO",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    public Address getAddress(double latitude, double longitude) {
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            return addresses.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }


    public void getAddress() {

        Address locationAddress = getAddress(latitude, longitude);

        if (locationAddress != null) {
            String address = locationAddress.getAddressLine(0);
            String address1 = locationAddress.getAddressLine(1);
            String city = locationAddress.getLocality();
            String state = locationAddress.getAdminArea();
            String country = locationAddress.getCountryName();
            String postalCode = locationAddress.getPostalCode();
             longitude = locationAddress.getLongitude();
             latitude = locationAddress.getLatitude();
            String currentLocation;
            String lat_str = String.valueOf(latitude);
            String log_str = String.valueOf(longitude);
            //IConsts.lat=latitude;
           // IConsts.lng=longitude;
            if (!TextUtils.isEmpty(address)) {
                currentLocation = address;

                if (!TextUtils.isEmpty(address1))
                    currentLocation += "\n" + address1;

                if (!TextUtils.isEmpty(city)) {
                    currentLocation += "\n" + city;

                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation += " - " + postalCode;
                } else {
                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation += "\n" + postalCode;
                }

                if (!TextUtils.isEmpty(lat_str))
                    currentLocation += "\n" + lat_str;
                if (!TextUtils.isEmpty(log_str))
                    currentLocation += "\n" + log_str;

                if (!TextUtils.isEmpty(state))
                    currentLocation += "\n" + state;

                if (!TextUtils.isEmpty(state))
                    currentLocation += "\n" + state;

                if (!TextUtils.isEmpty(country))
                    currentLocation += "\n" + country;

                tvEmpty.setVisibility(View.GONE);
                tvAddress.setText(currentLocation);
                tvAddress.setVisibility(View.VISIBLE);

               /* if(!btnProceed.isEnabled())
                    btnProceed.setEnabled(true);*/


            }

        }

    }

    /**
     * Creating google api client object
     */

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult locationSettingsResult) {

                final Status status = locationSettingsResult.getStatus();

                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location requests here
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(LocationActivity.this, REQUEST_CHECK_SETTINGS);

                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        break;
                }
            }
        });


    }


    /**
     * Method to verify google play services on the device
     */

    private boolean checkPlayServices() {

        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();

        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            if (googleApiAvailability.isUserResolvableError(resultCode)) {
                googleApiAvailability.getErrorDialog(this, resultCode,
                        PLAY_SERVICES_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        break;
                    default:
                        break;
                }
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        checkPlayServices();
    }

    /**
     * Google api callback methods
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {

        // Once connected with google api, get the location
        getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        mGoogleApiClient.connect();
    }


}
