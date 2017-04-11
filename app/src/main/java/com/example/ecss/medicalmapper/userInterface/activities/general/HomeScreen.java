package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
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
import com.example.ecss.medicalmapper.models.Clinic;
import com.example.ecss.medicalmapper.models.Hospital;
import com.example.ecss.medicalmapper.models.Laboratory;
import com.example.ecss.medicalmapper.models.MedicalPlace;
import com.example.ecss.medicalmapper.models.Pharmacy;
import com.example.ecss.medicalmapper.storage.databases.DatabaseHandler;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.ecss.medicalmapper.R.id.map;

public class HomeScreen extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnInfoWindowClickListener {

    private boolean filterHospital = true, filterLab = true, filterClinic = true, filterPharmacy = true;
    private static final String TAG = HomeScreen.class.getSimpleName();

    private GoogleMap mMap;
    private CameraPosition mCameraPosition;

    // The entry point to Google Play services, used by the Places API and Fused Location Provider.
    private GoogleApiClient mGoogleApiClient;

    // A request object to store parameters for requests to the FusedLocationProviderApi.
    private LocationRequest mLocationRequest;

    // The desired interval for location updates. Inexact. Updates may be more or less frequent.
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // The fastest rate for active location updates. Exact. Updates will never be more frequent
    // than this value.
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;


    private ArrayList<MedicalPlace> places = new ArrayList();
    private Map<String, MedicalPlace> Markers = new HashMap<String, MedicalPlace>();

    // The geographical location where the device is currently located.
    private Location mCurrentLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    public static Intent getIntent(Context context) {
        Intent intent = new Intent(context, HomeScreen.class);
        return intent;
    }
// on create function 5alefa beda

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.activity_home_screen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
///////////////////////////////////////////////////////////////////////////////////////////////////

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        /*SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        // Build the Play services client for use by the Fused Location Provider and the Places API.
        buildGoogleApiClient();
        mGoogleApiClient.connect();*/

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);

        // Build the Play services client for use by the Fused Location Provider and the Places API.
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        LongTask task = new LongTask();
        task.execute();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_hospitals) {
            filterClinic = false;
            filterLab = false;
            filterPharmacy = false;
            filterHospital = true;
            updateMarkers();
        } else if (id == R.id.nav_clinics) {
            filterClinic = true;
            filterLab = false;
            filterPharmacy = false;
            filterHospital = false;
            updateMarkers();
        } else if (id == R.id.nav_laboratories) {
            filterClinic = false;
            filterLab = true;
            filterPharmacy = false;
            filterHospital = false;
            updateMarkers();
        } else if (id == R.id.nav_pharmacies) {
            filterClinic = false;
            filterLab = false;
            filterPharmacy = true;
            filterHospital = false;
            updateMarkers();
        } else if (id == R.id.nav_signin) {
            Intent intent = new Intent(this, SignIn.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {
            /*Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.nav_my_places) {
            Intent intent = new Intent(this, SavedPlaces.class);
            startActivity(intent);
        } else if (id == R.id.nav_go_premium) {
            /*Intent intent = new Intent(this, PremiumActivity.class);
            startActivity(intent);*/
        } else if (id == R.id.nav_signout) {
            /*Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);*/
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
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

            Intent intent = new Intent(this, AdvancedSearch.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            LocationServices.FusedLocationApi.removeLocationUpdates(
                    mGoogleApiClient, this);
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
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Handles failure to connect to the Google Play services client.
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        // Refer to the reference doc for ConnectionResult to see what error codes might
        // be returned in onConnectionFailed.
        Log.d(TAG, "Play services connection failed: ConnectionResult.getErrorCode() = "
                + result.getErrorCode());
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

    @Override
    public void onInfoWindowClick(Marker marker) {


        if (Markers.get(marker.getId()) instanceof Hospital) {
            Intent intent = new Intent(getApplicationContext(), Details.class);
            intent.putExtra("Place", Markers.get(marker.getId()));
            startActivity(intent);
        }
        if (Markers.get(marker.getId()) instanceof Pharmacy) {
            Intent intent = new Intent(getApplicationContext(), Details.class);
            intent.putExtra("Place", Markers.get(marker.getId()));
            startActivity(intent);
        }
        if (Markers.get(marker.getId()) instanceof Clinic) {
            Intent intent = new Intent(getApplicationContext(), Details.class);
            intent.putExtra("Place", Markers.get(marker.getId()));
            startActivity(intent);
        }
        if (Markers.get(marker.getId()) instanceof Laboratory) {
            Intent intent = new Intent(getApplicationContext(), Details.class);
            intent.putExtra("Place", Markers.get(marker.getId()));
            startActivity(intent);
        }


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
            DatabaseHandler dp = new DatabaseHandler(this);

            ArrayList<Hospital> hp = dp.getHospitals();
            ArrayList<Pharmacy> ph = dp.getPharmacies();
            ArrayList<Laboratory> lab = dp.getLaboratories();
            ArrayList<Clinic> clinics = dp.getClinics();
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                public View getInfoWindow(Marker arg0) {

                    View v = getLayoutInflater().inflate(R.layout.custom_infowindow, null);

                    TextView title = (TextView) v.findViewById(R.id.info_window_title);
                    TextView doctor = (TextView) v.findViewById(R.id.info_window_doctor);
                    TextView specialization = (TextView) v.findViewById(R.id.info_window_specialization);
                    TextView appointment = (TextView) v.findViewById(R.id.info_window_appointments);

                    if (Markers.get(arg0.getId()) instanceof Hospital) {

                        Hospital h = (Hospital) Markers.get(arg0.getId());

                        title.setText("Hospital");
                        doctor.setText(h.Name);
                        specialization.setText(h.Specialization);

                    } else if (Markers.get(arg0.getId()) instanceof Pharmacy) {
                        Pharmacy ph = (Pharmacy) Markers.get(arg0.getId());

                        title.setText("Pharmacy");
                        doctor.setText(ph.Name);

                    } else if (Markers.get(arg0.getId()) instanceof Clinic) {
                        Clinic c = (Clinic) Markers.get(arg0.getId());

                        title.setText("Clinic");
                        doctor.setText(c.Doctor);
                        specialization.setText(c.Specialization);
                        appointment.setText(c.Appointments);

                    } else if (Markers.get(arg0.getId()) instanceof Laboratory) {
                        Laboratory l = (Laboratory) Markers.get(arg0.getId());

                        title.setText("Laboratory");
                        doctor.setText(l.Doctor);
                        specialization.setText(l.Specialization);
                        appointment.setText(l.Appointments);
                    }

                    return v;
                }

                public View getInfoContents(Marker arg0) {

                    return null;
                }
            });

            if (filterHospital) {
                for (final Hospital h : hp) {

                    Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(new LatLng(h.Latitude, h.Longitude))
                            .title("Hospital")
                            .snippet(h.Name)
                            .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.hospitalmarker))
                             );
                    marker.showInfoWindow();
                    Markers.put(marker.getId(), h);
                }
            }
            if (filterClinic) {
                for (Clinic clinic : clinics) {

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(clinic.Latitude, clinic.Longitude))
                                    .title("Clinic")
                                    .snippet(clinic.Doctor)
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicmarker))
                    );
                    marker.showInfoWindow();
                    Markers.put(marker.getId(), clinic);
                }
            }
            if (filterPharmacy) {
                for (Pharmacy pharmacy : ph) {

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(pharmacy.Latitude, pharmacy.Longitude))
                                    .title("pharmacy")
                                    .snippet(pharmacy.Name)
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.pharmacymarker))
                    );

                    marker.showInfoWindow();
                    Markers.put(marker.getId(), pharmacy);
                }
            }
            if (filterLab) {
                for (Laboratory laboratory : lab) {

                    Marker marker = mMap.addMarker(new MarkerOptions()
                                    .position(new LatLng(laboratory.Latitude, laboratory.Longitude))
                                    .title("laboratory")
                                    .snippet(laboratory.Name)
                                    .infoWindowAnchor(0.5f, 0.5f)
                            //.icon(BitmapDescriptorFactory.fromResource(R.drawable.laboratorymarker))
                    );

                    marker.showInfoWindow();
                    Markers.put(marker.getId(), laboratory);
                }
            }

        } else {
            mMap.addMarker(new MarkerOptions()
                    .position(mDefaultLocation)
                    .title(getString(R.string.default_info_title))
                    .snippet(getString(R.string.default_info_snippet)));
        }
    }


    void addToDatabase() {

        DatabaseHandler databaseHandler = new DatabaseHandler(this);
        Hospital h;
        Pharmacy p;
        Laboratory l;
        Clinic c;

        //Hospital 1
        h = new Hospital();
        h.Name = "El Marwa";
        h.Doctor = "Tarek Nabil El Sadfy";
        h.BuildingNumber = "12";
        h.Street = "El Tahrir";
        h.AddressNotes = "Mesaha square";
        h.PhoneNumber = "3349327";
        h.Latitude = 30.0342473;
        h.Longitude = 31.208059;
        h.Specialization = "General";
        places.add(h);
        databaseHandler.addHospital(h);

        //Hospital 2
        h = new Hospital();
        h.Name = "El Omam";
        h.Doctor = "Sayed El Akhras";
        h.BuildingNumber = "2";
        h.Street = "El Batal Ahmad Abdel Aziz Street";
        h.AddressNotes = "";
        h.PhoneNumber = "37615829";
        h.Latitude = 30.0510507;
        h.Longitude = 31.209422;
        h.Specialization = "Plastic surgery & laser";
        places.add(h);
        databaseHandler.addHospital(h);

        //Pharmacy 1
        p = new Pharmacy();
        p.Name = "Zien";
        p.BuildingNumber = "12";
        p.Street = "El Tahrir";
        p.AddressNotes = "Mesaha square";
        p.PhoneNumber = "3349327";
        p.Latitude = 30.0342473;
        p.Longitude = 31.208059;
        places.add(p);
        databaseHandler.addPharmacy(p);

        //Pharmacy 2
        p = new Pharmacy();
        p.Name = "Al Image";
        p.BuildingNumber = "31";
        p.Street = "Wezaret El Zeraa Street";
        p.AddressNotes = "Nasyt abdel razak mohamed street";
        p.PhoneNumber = "19770";
        p.Latitude = 30.04436;
        p.Longitude = 31.2119833;
        places.add(p);
        databaseHandler.addPharmacy(p);

        //Pharmacy 3
        p = new Pharmacy();
        p.Name = "Al Foad";
        p.BuildingNumber = "6";
        p.Street = "Wezaret El Zeraa Street";
        p.AddressNotes = "Nasyt Al Ahrar Street";
        p.PhoneNumber = "19705";
        p.Latitude = 30.0494056;
        p.Longitude = 31.2102718;
        places.add(p);
        databaseHandler.addPharmacy(p);

        //Pharmacy 4
        p = new Pharmacy();
        p.Name = "Roshdy";
        p.BuildingNumber = "31";
        p.Street = "El Batal Ahmad Abdel Aziz Street";
        p.AddressNotes = "";
        p.PhoneNumber = "19661";
        p.Latitude = 30.0534819;
        p.Longitude = 31.2058884;
        places.add(p);
        databaseHandler.addPharmacy(p);

        //Clinic 1
        c = new Clinic();
        c.Doctor = "Samir Abdel Baqy";
        c.BuildingNumber = "37";
        c.AppartmentNumber = "0";
        c.Street = "Wezaret El Zeraa Street";
        c.AddressNotes = "";
        c.PhoneNumber = "33354139";
        c.Latitude = 30.0448024;
        c.Longitude = 31.2123293;
        c.Specialization = "General Surgery";
        c.Appointments = "8:00 PM : 10:00 PM";
        c.ClosedDays = "Friday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Clinic 2
        c = new Clinic();
        c.Doctor = "Mohamed Kareem Sedky";
        c.BuildingNumber = "5";
        c.AppartmentNumber = "0";
        c.Street = "El Batal Ahmad Abdel Aziz Street";
        c.AddressNotes = "";
        c.PhoneNumber = "33462303";
        c.Latitude = 30.0514066;
        c.Longitude = 31.2088405;
        c.Specialization = "Ocular Surgery";
        c.Appointments = "8:00 PM : 10:00 PM ";
        c.ClosedDays = "Tuesday-Thursday-Friday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Clinic 3
        c = new Clinic();
        c.Doctor = "Ahmad Mohamed Sherif";
        c.BuildingNumber = "41";
        c.AppartmentNumber = "0";
        c.Street = "El Batal Ahmad Abdel Aziz Street";
        c.AddressNotes = "";
        c.PhoneNumber = "33020758";
        c.Latitude = 30.0538666;
        c.Longitude = 31.2053516;
        c.Specialization = "Ocular Surgery";
        c.Appointments = "7:00 PM : 10:00 PM ";
        c.ClosedDays = "Tuesday-Friday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Clinic 4
        c = new Clinic();
        c.Doctor = "Sayed Abd El latif";
        c.BuildingNumber = "145";
        c.AppartmentNumber = "3";
        c.Street = "El Tahrir";
        c.AddressNotes = "Mesaha square";
        c.PhoneNumber = "3349327";
        c.Latitude = 30.0342473;
        c.Longitude = 31.208059;
        c.Specialization = "Dentistry";
        c.Appointments = "4:00 PM : 11:00 PM";
        c.ClosedDays = "Friday - Saturday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Clinic 5
        c = new Clinic();
        c.Doctor = "Ahmed Charkaoui";

        c.BuildingNumber = "49";
        c.AppartmentNumber = "0";
        c.Street = "El Sudan";
        c.AddressNotes = "Above Kheir Zaman Supermarket - Dokki";

        c.PhoneNumber = "33361271";
        c.Latitude = 30.039325;
        c.Longitude = 31.196047;
        c.Specialization = "Ear Nose and Throat";
        c.Appointments = "4PM : 10PM";
        c.ClosedDays = "Saturday - Sunday - Monday - Tuesday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Clinic 6
        c = new Clinic();
        c.Doctor = "Ahmed Abdel Aal Asalmaoa";

        c.BuildingNumber = "9";
        c.AppartmentNumber = "0";
        c.Street = "El Tahrir";
        c.AddressNotes = "El Dokki";


        c.PhoneNumber = "33373425";
        c.Latitude = 30.038011;
        c.Longitude = 31.210006;
        c.Specialization = "Ear Nose and Throat";
        c.Appointments = "4PM : 10PM";
        c.ClosedDays = " Wednesday - Thursday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Clinic 7
        c = new Clinic();
        c.Doctor = " Ahmed Khalif";

        c.BuildingNumber = "98";
        c.AppartmentNumber = "3";
        c.Street = "El Tahrir";
        c.AddressNotes = "El Dokki square";

        c.PhoneNumber = "37620525";
        c.Latitude = 30.044693;
        c.Longitude = 31.238292;
        c.Specialization = "Orthopaedic Surgery";
        c.Appointments = "4PM : 10PM";
        c.ClosedDays = "Sunday - Monday - Tuesday";


        places.add(c);
        databaseHandler.addClinic(c);

        //clinic 8
        c = new Clinic();
        c.Doctor = "Samir Hussein Sharmy";

        c.BuildingNumber = "16";
        c.AppartmentNumber = "15";
        c.Street = "El Dokki";
        c.AddressNotes = "El Dokki ";

        c.PhoneNumber = "33355831";
        c.Latitude = 30.036749;
        c.Longitude = 31.174566;
        c.Specialization = "Orthopaedic Surgery";
        c.Appointments = "5pm : 8pm";
        c.ClosedDays = "Wednesday - Thursday";


        places.add(c);
        databaseHandler.addClinic(c);

        //clinic 9
        c = new Clinic();
        c.Doctor = "Tariq Ghnome";

        c.BuildingNumber = "42";
        c.AppartmentNumber = "0";
        c.Street = "El Dokki";
        c.AddressNotes = "Dokki Square - TOP Sednawi - Building Misr Insurance";

        c.PhoneNumber = "33375882";
        c.Latitude = 30.037436;
        c.Longitude = 31.211839;
        c.Specialization = "Poise and Auricular";
        c.Appointments = "12AM : 10PM";
        c.ClosedDays = "Sunday - monday - tuesday";

        places.add(c);
        databaseHandler.addClinic(c);

        //clinic 10
        c = new Clinic();
        c.Doctor = "Hazem Mohammad Yassin";

        c.BuildingNumber = "17";
        c.AppartmentNumber = "0";
        c.Street = "Shehab";
        c.AddressNotes = "El Mohandseen";

        c.PhoneNumber = "37490101";
        c.Latitude = 30.051202;
        c.Longitude = 31.195944;
        c.Specialization = "Eye surgery";
        c.Appointments = "1PM : 10PM";
        c.ClosedDays = "Saturday - Sunday - Monday - Tuesday";

        places.add(c);
        databaseHandler.addClinic(c);

        //Laboratory 1
        l = new Laboratory();

        l.Doctor = "Omaima  Gohar";

        l.BuildingNumber = "16";
        l.AppartmentNumber = "0";
        l.Street = "Al Mesaha";
        l.AddressNotes = "Mesaha Square - Dokki";

        l.PhoneNumber = "33365464";
        l.Latitude = 30.035552;
        l.Longitude = 31.214102;
        l.Specialization = "Medical tests";
        l.Appointments = "5PM : 8PM";
        l.ClosedDays = "Saturday - Sunday - Monday - Tuesday";

        places.add(l);
        databaseHandler.addLaboratory(l);

        //Laboratory 2
        l = new Laboratory();

        l.Doctor = "Tariq Mattawa";

        l.BuildingNumber = "100";
        l.AppartmentNumber = "0";
        l.Street = "El Tahrir";
        l.AddressNotes = "EL Dokki";

        l.PhoneNumber = "33375299";
        l.Latitude = 30.038011;
        l.Longitude = 31.210006;
        l.Specialization = "Medical tests";
        l.Appointments = "4pm : 10pm";
        l.ClosedDays = "Saturday - Sunday - Monday";

        places.add(l);
        databaseHandler.addLaboratory(l);

        //Laboratory 3
        l = new Laboratory();

        l.Doctor = "Inas Ismail Raafat";

        l.BuildingNumber = "22";
        l.AppartmentNumber = "0";
        l.Street = "Mossadak";
        l.AddressNotes = "EL Dokki";

        l.PhoneNumber = "33364505";
        l.Latitude = 30.039968;
        l.Longitude = 31.208748;
        l.Specialization = "Medical tests";
        l.Appointments = "4PM : 10PM";
        l.ClosedDays = "Saturday - Sunday - Thursday";

        places.add(l);
        databaseHandler.addLaboratory(l);

        //Laboratory 4
        l = new Laboratory();

        l.Doctor = "Amina Abdel Wahed Alhqnkira";

        l.BuildingNumber = "139";
        l.AppartmentNumber = "0";
        l.Street = "El Tahrir";
        l.AddressNotes = "EL Dokki";

        l.PhoneNumber = "33371819";
        l.Latitude = 30.038011;
        l.Longitude = 31.210006;
        l.Specialization = "Medical tests";
        l.Appointments = "4pm : 10pm";
        l.ClosedDays = " Wednesday - Thursday";

        places.add(l);
        databaseHandler.addLaboratory(l);

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

    private class LongTask extends AsyncTask<Void, Void, Void> {


        protected Void doInBackground(Void... urls) {

            addToDatabase();

            return null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... progress) {
            Log.e("Async Task", "Inside onProgressUpdate");
        }

        @Override
        protected void onPostExecute(Void result) {


            Log.e("Async Task", "Inside onPostExecute");
        }
    }
}
