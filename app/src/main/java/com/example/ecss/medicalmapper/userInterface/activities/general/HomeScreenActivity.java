package com.example.ecss.medicalmapper.userInterface.activities.general;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecss.medicalmapper.At3alegFenApplication;
import com.example.ecss.medicalmapper.R;
import com.example.ecss.medicalmapper.SettingsActivity;
import com.example.ecss.medicalmapper.chatbot.ChatBotActivity;
import com.example.ecss.medicalmapper.chattingsystem.ui.ChattingSystemActivity;
import com.example.ecss.medicalmapper.model.PlacesResponse;
import com.example.ecss.medicalmapper.model.place.Branch;
import com.example.ecss.medicalmapper.model.place.Clinic;
import com.example.ecss.medicalmapper.model.place.Hospital;
import com.example.ecss.medicalmapper.model.place.Laboratory;
import com.example.ecss.medicalmapper.model.place.MedicalPlace;
import com.example.ecss.medicalmapper.model.place.MedicalPlaces;
import com.example.ecss.medicalmapper.model.place.Pharmacy;
import com.example.ecss.medicalmapper.model.user.Doctor;
import com.example.ecss.medicalmapper.model.user.User;
import com.example.ecss.medicalmapper.network.ApiCallStatus;
import com.example.ecss.medicalmapper.network.ConnectivityReceiver;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.PlacesSearchBranch;
import com.example.ecss.medicalmapper.network.placesSearchApiCall.PlacesSearchResponse;
import com.example.ecss.medicalmapper.network.retrofit.ApiClient;
import com.example.ecss.medicalmapper.network.retrofit.ApiInterface;
import com.example.ecss.medicalmapper.network.showNearbyPlacesApiCall.CardinalPoints;
import com.example.ecss.medicalmapper.network.showNearbyPlacesApiCall.ShowNearbyPlacesRequest;
import com.example.ecss.medicalmapper.utility.Utility;
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
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ecss.medicalmapper.R.id.map;
import static com.example.ecss.medicalmapper.R.id.nav_signin;
import static com.example.ecss.medicalmapper.utility.Utility.checkInternetConnection;
import static com.example.ecss.medicalmapper.utility.Utility.getPremiumUserFromSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.getUserFromSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.savePremiumUserToSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.saveUserToSharedPreferences;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbar;
import static com.example.ecss.medicalmapper.utility.Utility.showSnackbarDisconnected;


public class HomeScreenActivity extends AppCompatActivity implements OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ConnectivityReceiver.ConnectivityReceiverListener,
        LocationListener, GoogleMap.OnMarkerClickListener {

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

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng DEFAULT_LOCATION = new LatLng(30.03848597645301, 31.211557388305662);

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    private BottomSheetBehavior mBottomSheetBehavior;

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

    private static final Integer HOSPITALS_API_CALL_NUMBER = 0;
    private static final Integer CLINICS_API_CALL_NUMBER = 1;
    private static final Integer LABORATORIES_API_CALL_NUMBER = 2;
    private static final Integer PHARMACIES_API_CALL_NUMBER = 3;
    private static final Integer ALL_API_CALL_NUMBER = 4;

    boolean mIsAdvancedSearchVisible = false;

    public static void startActivity(Context context) {
        if (context == null) {
            return;
        }
        context.startActivity(new Intent(context, HomeScreenActivity.class));
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
        setSupportActionBar(mToolbar);

        if (!checkInternetConnection()) {
            showSnackbarDisconnected(findViewById(android.R.id.content), this);
        }

        // NavigationDrawer code
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Toast.makeText(getApplicationContext(), "onDrawerOpened", Toast.LENGTH_LONG);
            }
        };

        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
///////////////////////////////////////////////////////////////////////////////////////////////////

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(map);
        mapFragment.getMapAsync(this);

        // Build the Play services client for use by the Fused Location Provider and the MedicalPlaces API.
        buildGoogleApiClient();
        mGoogleApiClient.connect();

        //Update navigation drawer items based on user type
        updateNavigationDrawerItems();

        //Create bottom sheet
        View bottomSheet = ButterKnife.findById(this, R.id.bottom_sheet);
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        mBottomSheetBehavior.setHideable(true);
        mBottomSheetBehavior.setPeekHeight(300);
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

        View bottomSheet = ButterKnife.findById(this, R.id.bottom_sheet);
        TextView titleTextView = ButterKnife.findById(bottomSheet, R.id.bottom_sheet_title);
        TextView placeNameTextView = ButterKnife.findById(bottomSheet, R.id.bottom_sheet_place_name);
        TextView threeTextView = ButterKnife.findById(bottomSheet, R.id.bottom_sheet_three);
        TextView fourTextView = ButterKnife.findById(bottomSheet, R.id.bottom_sheet_four);

        Button seeMoreButton = ButterKnife.findById(bottomSheet, R.id.bottom_sheet_seemore_btn);

        //DetailsActivity.startActivity(this, mMarkers.get(marker.getId()));
        final Context context = getApplicationContext();

        seeMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMarkers != null && marker != null && mMarkers.containsKey(marker.getId()) && mMarkers.get(marker.getId()) != null) {
                    DetailsActivity.startActivity(context, mMarkers.get(marker.getId()));
                }
            }
        });

        if (mMarkers.get(marker.getId()) instanceof Hospital) {

            Hospital hospital = (Hospital) mMarkers.get(marker.getId());

            titleTextView.setText(getString(R.string.hospital));
            placeNameTextView.setText(hospital.getHospitalName());
            threeTextView.setText(getString(R.string.governmental) + hospital.getIsGovernment());

            fourTextView.setVisibility(View.VISIBLE);
            fourTextView.setText(getString(R.string.emergency) + hospital.getHospitalEmergency());

        } else if (mMarkers.get(marker.getId()) instanceof Pharmacy) {
            Pharmacy pharmacy = (Pharmacy) mMarkers.get(marker.getId());

            titleTextView.setText(getString(R.string.pharmacy));
            placeNameTextView.setText(pharmacy.getPharmacyName());
            threeTextView.setText(pharmacy.getPharmacyHotline());

            fourTextView.setVisibility(View.VISIBLE);
            fourTextView.setText(getString(R.string.delivery) + pharmacy.getPharmacyDelivery());

        } else if (mMarkers.get(marker.getId()) instanceof Clinic) {
            Clinic clinic = (Clinic) mMarkers.get(marker.getId());

            titleTextView.setText(getString(R.string.clinic));
            placeNameTextView.setText(clinic.getClinicName());
            threeTextView.setText(clinic.getClinicSpecialization());

            fourTextView.setVisibility(View.GONE);

        } else if (mMarkers.get(marker.getId()) instanceof Laboratory) {
            Laboratory laboratory = (Laboratory) mMarkers.get(marker.getId());
            titleTextView.setText(getString(R.string.laboratory));
            placeNameTextView.setText(laboratory.getLabName());
            threeTextView.setText(laboratory.getLabSpecialization());

            fourTextView.setVisibility(View.VISIBLE);
            fourTextView.setText(laboratory.getLabHotline());
        }

        return true;
    }

    private void updateNavigationDrawerItems() {
        if (mNavigationView != null) {

            if (getUserFromSharedPreferences(this).getUserId() != -1 && getPremiumUserFromSharedPreferences(this).getUserId() != -1) {
                //Premium user
                mNavigationView.getMenu().findItem(R.id.nav_signin).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_signup).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_profile).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_my_places).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_chat).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_go_premium).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_add_place).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_signout).setVisible(true);

                mIsAdvancedSearchVisible = true;
            } else if (getUserFromSharedPreferences(this).getUserId() != -1) {
                //Normal user
                mNavigationView.getMenu().findItem(R.id.nav_signin).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_signup).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_profile).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_my_places).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_chat).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_go_premium).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_add_place).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_signout).setVisible(true);

                mIsAdvancedSearchVisible = true;

            } else {
                //Guest user
                mNavigationView.getMenu().findItem(R.id.nav_signin).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_signup).setVisible(true);
                mNavigationView.getMenu().findItem(R.id.nav_profile).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_my_places).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_chat).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_go_premium).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_add_place).setVisible(false);
                mNavigationView.getMenu().findItem(R.id.nav_signout).setVisible(false);

                mIsAdvancedSearchVisible = false;
            }
        }
    }

    private void saveRequestTypeToSharedPreference(Integer requestType) {

        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.request_type_shared_preference), Context.MODE_PRIVATE).edit();
        editor.putInt(getString(R.string.request_type_shared_preference_number), requestType);
        editor.commit();
    }

    private int getRequestTypeFromSharedPreference() {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.request_type_shared_preference), Context.MODE_PRIVATE);
        return sharedPref.getInt(getString(R.string.request_type_shared_preference_number), ALL_API_CALL_NUMBER);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            saveRequestTypeToSharedPreference(ALL_API_CALL_NUMBER);
            updateMarkers();
        } else if (id == R.id.nav_hospitals) {
            saveRequestTypeToSharedPreference(HOSPITALS_API_CALL_NUMBER);
            updateMarkers();
        } else if (id == R.id.nav_clinics) {
            saveRequestTypeToSharedPreference(CLINICS_API_CALL_NUMBER);
            updateMarkers();
        } else if (id == R.id.nav_laboratories) {
            saveRequestTypeToSharedPreference(LABORATORIES_API_CALL_NUMBER);
            updateMarkers();
        } else if (id == R.id.nav_pharmacies) {
            saveRequestTypeToSharedPreference(PHARMACIES_API_CALL_NUMBER);
            updateMarkers();
        } else if (id == nav_signin) {
            SignInActivity.startActivity(this);
        } else if (id == R.id.nav_signup) {
            SignUpActivity.startActivity(this);
        } else if (id == R.id.nav_profile) {
            DoctorProfileActivity.startActivity(this);
        } else if (id == R.id.nav_my_places) {
            SavedPlacesActivity.startActivity(this);
        } else if (id == R.id.nav_chat) {
            ChattingSystemActivity.startActivity(this);
        } else if (id == R.id.nav_go_premium) {
            DoctorSignUpActivity.startActivity(this);
        } else if (id == R.id.nav_add_place) {
            AddPlaceActivity.startActivity(this);
        } else if (id == R.id.nav_signout) {

            Doctor doctor = getPremiumUserFromSharedPreferences(this);
            if (doctor.getUserId() != -1) {
                doctor.setUserId(-1);
                doctor.setDoctorName("-1");
                doctor.setDoctorSpecialization("-1");
                doctor.setDoctorDegree("-1");
                doctor.setDoctorOverview("-1");
                doctor.setDoctorPhoto("-1");
                doctor.setDoctorCertifications("-1");

                savePremiumUserToSharedPreferences(doctor, this);
            }

            User user = getUserFromSharedPreferences(this);
            if (user.getUserId() != -1) {

                user.setUserId(-1);
                user.setUserName("-1");
                user.setUserEmail("-1");
                user.setUserType(-1);
                user.setUserSavedPlace(-1);

                saveUserToSharedPreferences(user, this);
            }

        //    ChattingSystemActivity.logout();

            updateNavigationDrawerItems();
        }
        else if (id == R.id.nav_settings) {
            SettingsActivity.startActivity(this);
        } else if (id == R.id.nav_help) {
            ChatBotActivity.startActivity(this);
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

        if (mIsAdvancedSearchVisible) {
            MenuItem advancedSearchView = menu.findItem(R.id.action_search_plus);
            advancedSearchView.setVisible(true);
        }

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                final Context context = getApplicationContext();
                if (checkInternetConnection()) {
                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<PlacesSearchResponse> call = apiService.PlacesSearch(query);
                    call.enqueue(new Callback<PlacesSearchResponse>() {

                        @Override
                        public void onResponse(Call<PlacesSearchResponse> call, Response<PlacesSearchResponse> response) {
                            ApiCallStatus status = null;
                            List<PlacesSearchBranch> branches = null;

                            if (response.body() != null) {
                                status = response.body().getApiCallStatus();
                                branches = response.body().getBranches();
                            }

                            if (status != null && branches != null && status.getIsSuccessful()) {

                                DisplaySearchPlacesActivity.startActivity(context, branches);

                            } else {
                                if (status != null && status.getErrorStatus() != null) {
                                    //Toast.makeText(getApplicationContext(), status.getErrorStatus(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<PlacesSearchResponse> call, Throwable t) {
                            //Toast.makeText(getApplicationContext(), getString(R.string.internet_connection_problem_message), Toast.LENGTH_LONG).show();
                        }
                    });
                }
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
        if (id == R.id.action_search_plus) {
            AdvancedSearchActivity.startActivity(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            mMap.clear();
            getMedicalPlacesFromApi(getRequestTypeFromSharedPreference());

        } else {

        }
    }

    private void putMedicalPlacesOnMap(MedicalPlaces places) {

        List<Hospital> hospitals = places.getHospitals();
        List<Pharmacy> pharmacies = places.getPharmacies();
        List<Clinic> clinics = places.getClinics();
        List<Laboratory> laboratories = places.getLaboratories();

        if (hospitals != null) {
            for (Hospital place : hospitals) {
                putMedicalPlaceBranchesOnMap(place);
            }
        }
        if (pharmacies != null) {
            for (Pharmacy place : pharmacies) {
                putMedicalPlaceBranchesOnMap(place);
            }
        }
        if (clinics != null) {
            for (Clinic place : clinics) {
                putMedicalPlaceBranchesOnMap(place);
            }
        }
        if (laboratories != null) {
            for (Laboratory place : laboratories) {
                putMedicalPlaceBranchesOnMap(place);
            }
        }
    }

    private void putMedicalPlaceBranchesOnMap(MedicalPlace place) {
        if (place instanceof Hospital) {

            Hospital hospital = (Hospital) place;
            List<Branch> branches = hospital.getBranches();

            for (Branch branch : branches) {

                String latitude = branch.getBranchLatitude();
                String longitude = branch.getBranchLongitude();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.hospitalicon))
                );

                List<Branch> oneBranch = new ArrayList<>();
                oneBranch.add(branch);
                hospital.setBranches(oneBranch);

                mMarkers.put(marker.getId(), hospital);
            }

        } else if (place instanceof Pharmacy) {
            Pharmacy pharmacy = (Pharmacy) place;
            List<Branch> branches = pharmacy.getBranches();
            for (Branch branch : branches) {
                String latitude = branch.getBranchLatitude();
                String longitude = branch.getBranchLongitude();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.pharmacyicon))
                );

                List<Branch> oneBranch = new ArrayList<>();
                oneBranch.add(branch);
                pharmacy.setBranches(oneBranch);

                mMarkers.put(marker.getId(), pharmacy);
            }

        } else if (place instanceof Clinic) {
            Clinic clinic = (Clinic) place;
            List<Branch> branches = clinic.getBranches();

            for (Branch branch : branches) {
                String latitude = branch.getBranchLatitude();
                String longitude = branch.getBranchLongitude();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.clinicicon))
                );

                List<Branch> oneBranch = new ArrayList<>();
                oneBranch.add(branch);
                clinic.setBranches(oneBranch);

                mMarkers.put(marker.getId(), clinic);
            }

        } else if (place instanceof Laboratory) {

            Laboratory laboratory = (Laboratory) place;
            List<Branch> branches = laboratory.getBranches();

            for (Branch branch : branches) {
                String latitude = branch.getBranchLatitude();
                String longitude = branch.getBranchLongitude();

                Marker marker = mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude)))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.labicon))
                );

                List<Branch> oneBranch = new ArrayList<>();
                oneBranch.add(branch);
                laboratory.setBranches(oneBranch);

                mMarkers.put(marker.getId(), laboratory);
            }
        }
    }

    private void getMedicalPlacesFromApi(Integer PlacesType) {
        if (checkInternetConnection()) {
            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

            LatLngBounds curScreen = mMap.getProjection().getVisibleRegion().latLngBounds;
            String north = Double.toString(curScreen.northeast.latitude);
            String east = Double.toString(curScreen.northeast.longitude);
            String south = Double.toString(curScreen.southwest.latitude);
            String west = Double.toString(curScreen.southwest.longitude);

            CardinalPoints cardinalPoints = new CardinalPoints(north, south, east, west);

            cardinalPoints = new CardinalPoints("100.00", "0.00", "100.00", "0.00");

            Call<PlacesResponse> call = apiService.ShowNearbyPlaces(new ShowNearbyPlacesRequest(Utility.getLanguageFromSettings(this), cardinalPoints, PlacesType));

            call.enqueue(new Callback<PlacesResponse>() {
                @Override
                public void onResponse(Call<PlacesResponse> call, Response<PlacesResponse> response) {

                    MedicalPlaces places = null;
                    ApiCallStatus apiCallStatus = null;

                    if (response.body() != null) {
                        places = response.body().getMedicalPlaces();
                        apiCallStatus = response.body().getApiCallStatus();
                    }

                    if (apiCallStatus != null && places != null && apiCallStatus.getIsSuccessful()) {
                        putMedicalPlacesOnMap(places);
                    } else {
                        if (apiCallStatus != null && apiCallStatus.getErrorStatus() != null)
                            Toast.makeText(getApplicationContext(), apiCallStatus.getErrorStatus(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<PlacesResponse> call, Throwable t) {
                    //Toast.makeText(getApplicationContext(), getString(R.string.internet_connection_problem_message), Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnackbar(isConnected, findViewById(android.R.id.content), this);
    }


    /**
     * Get the device location and nearby places when the activity is restored after a pause.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        At3alegFenApplication.getInstance().setConnectivityListener(this);

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
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, DEFAULT_ZOOM));
            mMap.getUiSettings().setMyLocationButtonEnabled(false);
        }

        mMap.setOnMarkerClickListener(this);
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
