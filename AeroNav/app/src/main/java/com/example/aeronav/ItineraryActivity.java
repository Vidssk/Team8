package com.example.aeronav;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ItineraryActivity extends MainActivity {
    //Navigation Variable Declarations
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;
    TextView textView;
    private static final String BASE_URL = "https://api.yelp.com/v3/businesses/search";
    private static final String API_KEY = "hSjppTWkTW0YtwTt1G-IPp5EU_PU8nVF_nmFzxWpLZ8iFH-KKgTXNBE2H57P4iBwoRv76sMNB0iziZF0msnUoARBRZ4Bhuru2oFURfQ7PAGXeGGZ3IU3DL_2Oh0qZnYx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_itinerary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Navigation Hooks
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        Button btn_addItinerary = findViewById(R.id.btn_add_itinerary);
        btn_addItinerary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ItinerariesActivity.class);
                // Add Data to Either Database or create a new file with the Itinerary Data.
                startActivity(intent);
                finish();
            }
        });

        //Navbar Setup
        NavigationSetup();

        AuthenticationCheck();


        // Data from Packages
        //Get Survey Information
        String startLocation = getIntent().getStringExtra("keyStartLocation");
        String startDate = getIntent().getStringExtra("keyStartDate");
        String endDate = getIntent().getStringExtra("keyEndDate");
//        int[] startVacation= DateStringSplitter(Objects.requireNonNull(getIntent().getStringExtra("keyStartDate")));
        String EndLocation = getIntent().getStringExtra("keyEndLocation");
        String latitude = getIntent().getStringExtra("keyEndLatitude");
        String longitude = getIntent().getStringExtra("keyEndLongitude");

//        String url = BASE_URL + "?term=restaurants&latitude=" + latitude + "&longitude=" + longitude;
        String url = BASE_URL + "?term=restaurants&location="  + EndLocation;
        // Create the request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray businesses = response.getJSONArray("businesses");
                            StringBuilder restaurantDetails = new StringBuilder();
                            ScrollView scrollView = findViewById(R.id.mainScroll);
                            int day = 1;
                            for (int i = 0; i < businesses.length(); i++) {
                                if(i % 3 ==0 & day !=6){
                                    restaurantDetails.append("Day ").append(day++).append("\n");}
                                JSONObject business = businesses.getJSONObject(i);
                                String name = business.getString("name");
                                double rating = business.getDouble("rating");
                                String address = business.getJSONObject("location").getString("address1");

                                // Format the restaurant details
                                String formattedDetails = String.format("Name: %s\nRating: %.1f\nAddress: %s\n\n", name, rating, address);
                                restaurantDetails.append(formattedDetails);

                                // Display the formatted restaurant details in a TextView
//                                TextView textView = new TextView(ItineraryActivity.this);
//                                textView.setLayoutParams(new ViewGroup.LayoutParams(
//                                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                                        ViewGroup.LayoutParams.WRAP_CONTENT));
//                                textView.setText(formattedDetails);
//                                scrollView.addView(textView);

                            }


// Add the TextView to the container
                            TextView textView = findViewById(R.id.user_details);
                            textView.setText(restaurantDetails.toString());
                        } catch (JSONException e) {
                            Log.e(TAG,"Exception found");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e(TAG,"Error: " + latitude + ","+ longitude);
                    }

                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                // Set API key in request headers
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + API_KEY);
                return headers;
            }
        };

        // Add the request to the Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);



//        textView.setText(temp);







    }
    //Checks if there is a current user logged In
    private void AuthenticationCheck() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user == null) {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            Toast.makeText(this, "Need to Log in", Toast.LENGTH_SHORT).show();
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
    private int[] DateStringSplitter(String Date) {
        // Splitting the temp string using dot as delimiter
        String[] parts = Date.split("\\.");
// Extracting date, month, and year
        String yearString = parts[0];
        String monthString = parts[1];
        String dayString = parts[2];
// Converting strings to integers
        int year = Integer.parseInt(yearString);
        int month = Integer.parseInt(monthString);
        int day = Integer.parseInt(dayString);
        return new int[]{month,day,year};
    }
    public int calculateTripDuration(int startMonth, int startDay, int endMonth, int endDay) {
        int daysInStartMonth = getDaysInMonth(startMonth);
        int daysInEndMonth = getDaysInMonth(endMonth);

        // Calculate the total number of days in the start month
        int daysFromStart = daysInStartMonth - startDay;

        // Calculate the total number of days in the end month

        // Calculate the total number of days between the start and end months
        int daysBetweenMonths = 0;
        for (int i = startMonth + 1; i < endMonth; i++) {
            daysBetweenMonths += getDaysInMonth(i);
        }

        // Calculate the total duration of the trip in days

        return daysFromStart + daysBetweenMonths + endDay;
    }

    private int getDaysInMonth(int month) {
        // Define an array with the number of days in each month (assuming non-leap year)
        int[] daysInMonth = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysInMonth[month];
    }
}