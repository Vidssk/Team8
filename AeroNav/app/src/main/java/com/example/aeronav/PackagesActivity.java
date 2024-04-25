package com.example.aeronav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PackagesActivity extends MainActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView textView, tripView;
    LinearLayout btn_pack1, btn_pack2, btn_pack3, btn_pack4, btn_pack5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_packages);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // Navigation Hooks
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        //Navbar Setup
        NavigationSetup();

        AuthenticationCheck();

        //Get Survey Information
        String startLocation = getIntent().getStringExtra("keyStartLocation");
        String endLocation = getIntent().getStringExtra("keyEndLocation");
        String startDate = getIntent().getStringExtra("keyStartDate");
        String endDate = getIntent().getStringExtra("keyEndDate");

        //Also have access to latitude and longitude.


        String trip = startLocation +"-"+ endLocation;
        // For loop could be implemented for multiple setups.
        for (int i : new int[]{R.id.destination1, R.id.destination2, R.id.destination3, R.id.destination4, R.id.destination5}) {
            tripView = findViewById(i);
            tripView.setText(trip);
        }

        //Temporary 1 package
        btn_pack1 = findViewById(R.id.package1);
        tripView = findViewById(R.id.destination1);
        btn_pack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent = new Intent(getApplicationContext(),ItineraryActivity.class);
             intent.putExtra("keyPackage","Package");
             startActivity(intent);
             finish();
            }
        });

    }
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
    // Where you generate the packages. So far im only gonna generate 5 as seen above. Let me know if you can fix that or want me to.
    private void GeneratePackage(String startLocation, String endLocation, String startDate, String endDate) {

    }
}