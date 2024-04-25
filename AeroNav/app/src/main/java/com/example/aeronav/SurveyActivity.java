package com.example.aeronav;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.List;

public class SurveyActivity extends MainActivity implements OnMapReadyCallback {
    private GoogleMap myMap1, myMap2;
    TextView textView;
    String startLocation;
    String endLocation;
    private SearchView mySearch1View,mySearch2View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_survey);
        MapSetup();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Authentication Variables

        // Navigation Hooks
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //Google Maps Hooks
        mySearch1View = findViewById(R.id.map1Search);
        mySearch2View = findViewById(R.id.map2Search);

        //Survey Hooks
        Button btn_startDate = findViewById(R.id.startDateButton);
        Button btn_endDate = findViewById(R.id.endDateButton);
        Button btn_submit = findViewById(R.id.submitPackage);

        textView = findViewById(R.id.surveyData);


        //Navbar Setup
        NavigationSetup();

        AuthenticationCheck();
//        else {
//            textView.setText(user.getEmail());
//        }


        mySearch1View.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = mySearch1View.getQuery().toString();
                List<Address> addressList = null;
                if(location != null) {
                    Geocoder geocoder = new Geocoder(SurveyActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert addressList != null;
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    myMap1.addMarker(new MarkerOptions().position(latLng).title(location));
                    myMap1.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    startLocation = address.getLocality();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mySearch2View.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location = null;
                location = mySearch2View.getQuery().toString();
                List<Address> addressList = null;
                if(location != null) {
                    Geocoder geocoder = new Geocoder(SurveyActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert addressList != null;
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    myMap2.addMarker(new MarkerOptions().position(latLng).title(location));
                    myMap2.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                    endLocation = address.getLocality();
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //Button Event listeners
        btn_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(btn_startDate);

            }
        });
        btn_endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(btn_endDate);
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startDate = btn_startDate.getText().toString();
                String endDate = btn_endDate.getText().toString();
//                    <string name="start_date">Start Date</string>
//    <string name="end_date">End Date</string>
                if(startLocation.isEmpty() || startDate.equals("Start Date") || endLocation.isEmpty() || endDate.equals("End Date")){
                    Toast.makeText(SurveyActivity.this, "Fields cannot be empty.", Toast.LENGTH_SHORT).show();
                } else {
                    PassSurveyData(startDate,endDate);
                }
            }
        });

    }



    private void AuthenticationCheck() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    }

    private void NavigationSetup() {
        navigationView.setNavigationItemSelectedListener(this);
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }

    //Function for start and end Dates
    private void openDialog(Button btn){
        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Increment month by 1 to represent the correct month
                month += 1;
                String temp = month + "/" + dayOfMonth + "/" + year;
                btn.setText(temp);
            }
        }, 2022, 1, 15);
        dialog.show();
    }

    // Moved Generate Package to PackagesActivity.java
    //intent functions could be useful. Might be one to transport full package of what you generate
    // Function Passes survey data to the packages page.
    public void PassSurveyData(String startDate, String endDate) {
        // Get the position of the marker on myMap1
        LatLng position = myMap1.getCameraPosition().target;

        // Extract latitude and longitude from the position
        double latitude = position.latitude;
        double longitude = position.longitude;

        // Create the intent and pass data to PackagesActivity
        Intent intent = new Intent(SurveyActivity.this, PackagesActivity.class);
        intent.putExtra("keyStartLatitude", latitude);
        intent.putExtra("keyStartLongitude", longitude);
        intent.putExtra("keyStartLocation",startLocation);
        intent.putExtra("keyEndLocation",endLocation);
        position = myMap2.getCameraPosition().target;
        latitude = position.latitude;
        longitude = position.longitude;
        intent.putExtra("keyEndLatitude", latitude);
        intent.putExtra("keyEndLongitude", longitude);
        intent.putExtra("keyStartDate", startDate);
        intent.putExtra("keyEndDate", endDate);
        startActivity(intent);
        finish();
    }



    // Google Maps Functionality
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        if (myMap1 == null) {
            myMap1 = googleMap;
            // Initialize settings for the start map
        } else if (myMap2 == null) {
            myMap2 = googleMap;
            // Initialize settings for the end map
        }
    }

    private void MapSetup() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.startMap);
        SupportMapFragment mapFragment2 = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.endMap);
        assert mapFragment != null;
        mapFragment.getMapAsync(SurveyActivity.this);
        assert mapFragment2 != null;
        mapFragment2.getMapAsync(SurveyActivity.this);
    }
}