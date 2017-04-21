package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.model.place.Clinic;
import com.example.ecss.medicalmapper.model.place.Hospital;
import com.example.ecss.medicalmapper.model.place.Laboratory;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.model.place.Pharmacy;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.ecss.medicalmapper.R.id.map;


public class HomeScreenActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = HomeScreenActivity.class.getSimpleName();

    // The desired interval for location updates. Inexact. Updates may be more or less frequent.
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // The fastest rate for active location updates. Exact. Updates will never be more frequent
    // than this value.
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";
    private static final String EXTRA_PLACE = "Place";
    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(30.03848597645301, 31.211557388305662);
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private boolean mFilterHospital = true, mFilterLab = true, mFilterClinic = true, mFilterPharmacy = true;
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;
    // The entry point to Google Play services, used by the Places API and Fused Location Provider.
    private GoogleApiClient mGoogleApiClient;
    // A request object to store parameters for requests to the FusedLocationProviderApi.
    private LocationRequest mLocationRequest;
    private boolean mLocationPermissionGranted;
    private Map<String, MedicalPlace> mMarkers = new HashMap<String, MedicalPlace>();
    // The geographical location where the device is currently located.
    private Location mCurrentLocation;

    public static void startActivity(Context context, MedicalPlace place) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, DetailsActivity.class).putExtra(EXTRA_PLACE, place));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }
        setContentView(R.layout.activity_home_screen);

        ButterKnife.bind(this);

        //mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        // NavigationDrawer code
//        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
///////////////////////////////////////////////////////////////////////////////////////////////////

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        // Build the Play services client for use by the Fused Location Provider and the Places API.
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        //LongTask task = new LongTask();
        //task.execute();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hospitals) {
            mFilterClinic = false;
            mFilterLab = false;
            mFilterPharmacy = false;
            mFilterHospital = true;
            updateMarkers();
        } else if (id == R.id.nav_clinics) {
            mFilterClinic = true;
            mFilterLab = false;
            mFilterPharmacy = false;
            mFilterHospital = false;
            updateMarkers();
        } else if (id == R.id.nav_laboratories) {
            mFilterClinic = false;
            mFilterLab = true;
            mFilterPharmacy = false;
            mFilterHospital = false;
            updateMarkers();
        } else if (id == R.id.nav_pharmacies) {
            mFilterClinic = false;
            mFilterLab = false;
            mFilterPharmacy = true;
            mFilterHospital = false;
            updateMarkers();
        } else if (id == R.id.nav_signin) {
            startActivity(new Intent(this, SignInActivity.class));
        } else if (id == R.id.nav_profile) {
            /*Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.nav_my_places) {
            startActivity(new Intent(this, SavedPlacesActivity.class));
        } else if (id == R.id.nav_go_premium) {
            /*Intent intent = new Intent(this, PremiumActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.nav_signout) {
            /*Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);*/
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_screen, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_search_plus) {
            startActivity(new Intent(this, AdvancedSearchActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        if (mMarkers != null && marker != null && mMarkers.containsKey(marker.getId()) && mMarkers.get(marker.getId()) != null) {
            DetailsActivity.startActivity(this, (Hospital) mMarkers.get(marker.getId()));
        }
        /*if (mMarkers != null && marker != null && mMarkers.containsKey(marker.getId()) && mMarkers.get(marker.getId()) instanceof Pharmacy) {

            DetailsActivity.startActivity(this, (Pharmacy) mMarkers.get(marker.getId()));
        }
        if (mMarkers != null && marker != null && mMarkers.containsKey(marker.getId()) && mMarkers.get(marker.getId()) instanceof Clinic) {
            DetailsActivity.startActivity(this, (Clinic) mMarkers.get(marker.getId()));
        }
        if (mMarkers != null && marker != null && mMarkers.containsKey(marker.getId()) && mMarkers.get(marker.getId()) instanceof Laboratory) {
            DetailsActivity.startActivity(this, (Laboratory) mMarkers.get(marker.getId()));
        }*/
    }

    /**
     * Adds markers for places nearby the device and turns the My Location feature on or off,
     * provided location permission has been granted.
     */

    private void updateMarkers() {
        if (mMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            // Get the businesses and other points of interest located
            // nearest to the device's current location.

            //Fill the ArrayLists using Retrofit
            ArrayList<Hospital> hp = new ArrayList<>();
            ArrayList<Pharmacy> ph = new ArrayList<>();
            ArrayList<Laboratory> lab = new ArrayList<>();
            ArrayList<Clinic> clinics = new ArrayList<>();

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                public View getInfoWindow(Marker arg0) {

                    View v = getLayoutInflater().inflate(R.layout.custom_infowindow, null);

                    TextView title = (TextView) v.findViewById(R.id.info_window_title);
                    TextView doctor = (TextView) v.findViewById(R.id.info_window_doctor);
                    TextView specialization = (TextView) v.findViewById(R.id.info_window_specialization);
                    TextView appointment = (TextView) v.findViewById(R.id.info_window_appointments);

                    if (mMarkers.get(arg0.getId()) instanceof Hospital) {

                        Hospital h = (Hospital) mMarkers.get(arg0.getId());

                        title.setText("Hospital");
                        doctor.setText(h.getmHospitalName());
                        specialization.setText("General");

                    } else if (mMarkers.get(arg0.getId()) instanceof Pharmacy) {
                        Pharmacy ph = (Pharmacy) mMarkers.get(arg0.getId());

                        title.setText("Pharmacy");
                        doctor.setText(ph.getmPharmacyName());

                    } else if (mMarkers.get(arg0.getId()) instanceof Clinic) {
                        Clinic c = (Clinic) mMarkers.get(arg0.getId());

                        title.setText("Clinic");
                        doctor.setText(c.getmClinicName());
                        specialization.setText(c.getmClinicSpecialization());
                        //appointment.setText(c.get());

                    } else if (mMarkers.get(arg0.getId()) instanceof Laboratory) {
                        Laboratory l = (Laboratory) mMarkers.get(arg0.getId());

                        title.setText("Laboratory");
                        doctor.setText(l.getmLabName());
                        specialization.setText(l.getmLabSpecialization());
                        // appointment.setText(l.get());
                    }

                    return v;
                }

                public View getInfoContents(Marker arg0) {

                    return null;
                }
            });

            if (mFilterHospital) {
                for (final Hospital h : hp) {
                    String lat = h.getmBranches().get(h.getmHospitalId()).getmBranchLatitude();
                    String lon = h.getmBranches().get(h.getmHospitalId()).getmBranchLongitude();

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                    .title("Hospital")
                                    .snippet(h.getmHospitalName())
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.hospitalmarker))
                    );
                    marker.showInfoWindow();
                    mMarkers.put(marker.getId(), h);
                }
            }
            if (mFilterClinic) {
                for (Clinic clinic : clinics) {

                    String lat = clinic.getmBranches().get(clinic.getmClinicId()).getmBranchLatitude();
                    String lon = clinic.getmBranches().get(clinic.getmClinicId()).getmBranchLongitude();

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                    .title("Clinic")
                                    .snippet(clinic.getmClinicName())
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicmarker))
                    );
                    marker.showInfoWindow();
                    mMarkers.put(marker.getId(), clinic);
                }
            }
            if (mFilterPharmacy) {
                for (Pharmacy pharmacy : ph) {

                    String lat = pharmacy.getmBranches().get(pharmacy.getmPharmacyId()).getmBranchLatitude();
                    String lon = pharmacy.getmBranches().get(pharmacy.getmPharmacyId()).getmBranchLongitude();

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                    .title("pharmacy")
                                    .snippet(pharmacy.getmPharmacyName())
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.pharmacymarker))
                    );

                    marker.showInfoWindow();
                    mMarkers.put(marker.getId(), pharmacy);
                }
            }
            if (mFilterLab) {
                for (Laboratory laboratory : lab) {

                    String lat = laboratory.getmBranches().get(laboratory.getmLabId()).getmBranchLatitude();
                    String lon = laboratory.getmBranches().get(laboratory.getmLabId()).getmBranchLongitude();

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(Double.parseDouble(lat), Double.parseDouble(lon)))
                                    .title("laboratory")
                                    .snippet(laboratory.getmLabName())
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.laboratorymarker))
                    );

                    marker.showInfoWindow();
                    mMarkers.put(marker.getId(), laboratory);
                }
            }

        } else {
            mMap.addMarker(new MarkerOptions()
                    .position(mDefaultLocation)
                    .title(getString(R.string.default_info_title))
                    .snippet(getString(R.string.default_info_snippet)));
        }
    }

    /**
     * Get the device location and nearby places when the activity is restored after a pause.
     */
    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {
            getDeviceLocation();
        }
        updateMarkers();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onBackPressed() {
        //     DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mCurrentLocation);
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Gets the device's current location and builds the map
     * when the Google Play services client is successfully connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        getDeviceLocation();
        // Build the map.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Handles failure to connect to the Google Play services client.
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // Refer to the reference doc for ConnectionResult to see what error codes might
        // be returned in onConnectionFailed.
        Log.d(TAG, "Play services connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    /**
     * Handles suspension of the connection to the Google Play services client.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Play services connection suspended");
    }

    @Override
    public void onLocationChanged(Location location) {
        mCurrentLocation = location;
        updateMarkers();
    }


    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();
        // Add markers for nearby places.
        updateMarkers();


        /*
         * Set the map's camera position to the current location of the device.
         * If the previous state was saved, set the position to the saved state.
         * If the current location is unknown, use a default position and zoom value.
         */
        if (mCameraPosition != null) {
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(mCameraPosition));
        } else if (mCurrentLocation != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(mCurrentLocation.getLatitude(),
                            mCurrentLocation.getLongitude()), DEFAULT_ZOOM));
        } else {
            Log.d(TAG, "Current location is null. Using defaults.");
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

        mMap.setOnInfoWindowClickListener(this);
    }

    /**
     * Builds a GoogleApiClient.
     * Uses the addApi() method to request the Google Places API and the Fused Location Provider.
     */
    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */,
                        this /* OnConnectionFailedListener */)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .build();
        createLocationRequest();
    }

    /**
     * Sets up the location request.
     */
    private void createLocationRequest() {
        mLocationRequest = new LocationRequest();

        /*
         * Sets the desired interval for active location updates. This interval is
         * inexact. You may not receive updates at all if no location sources are available, or
         * you may receive them slower than requested. You may also receive updates faster than
         * requested if other applications are requesting location at a faster interval.
         */
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        /*
         * Sets the fastest rate for active location updates. This interval is exact, and your
         * application will never receive updates faster than this value.
         */
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    /**
     * Gets the current location of the device and starts the location update notifications.
     */
    private void getDeviceLocation() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         * Also request regular updates about the device location.
         */
        if (mLocationPermissionGranted) {
            mCurrentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient,
                    mLocationRequest, this);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }


    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    @SuppressWarnings("MissingPermission")
    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }

        if (mLocationPermissionGranted) {
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);
        } else {
            mMap.setMyLocationEnabled(false);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
            mCurrentLocation = null;
        }
    }
}
