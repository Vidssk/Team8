package com.example.aeronav;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SurveyActivity extends MainActivity {


    TextView textView;
    String startLocation;
    String endLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_survey);
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



        //Survey Hooks
        Button btn_startDate = findViewById(R.id.startDateButton);
        Button btn_endDate = findViewById(R.id.endDateButton);
        Button btn_submit = findViewById(R.id.submitPackage);
        EditText startLoc = findViewById(R.id.startLocationButton);
        EditText endLoc = findViewById(R.id.endLocationButton);
        textView = findViewById(R.id.surveyData);


        //Navbar Setup
        NavigationSetup();

        AuthenticationCheck();
//        else {
//            textView.setText(user.getEmail());
//        }

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
                startLocation = startLoc.getText().toString().trim();
                endLocation = endLoc.getText().toString().trim();
                String startDate = btn_startDate.getText().toString().trim();
                String endDate = btn_endDate.getText().toString().trim();
//                    <string name="start_date">Start Date</string>
//    <string name="end_date">End Date</string>
                if(startLocation.isEmpty() || startLocation.equals("Start Date")){
                   startLoc.setError("Starting Location cannot be empty");
                } else if (endLocation.isEmpty() || endLocation.equals("End Date")) {
                    endLoc.setError("Ending location cannot be empty.");
                } else {
                    generatePackage(startLocation,endLocation,startDate,endDate);
                }
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

    //Function for start and end Dates
    private void openDialog(Button btn){
        DatePickerDialog dialog = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String temp = String.valueOf(year)+"."+String.valueOf(month)+"."+String.valueOf(dayOfMonth);
//                textView.setText(temp);
                btn.setText(temp);

            }

        }, 2022, 1, 15);
        dialog.show();
    }
    // Where we generate a package and switch pages to that package.
    public void generatePackage(String startLocation,String endLocation, String startDate, String endDate){
        String temp = '['+startLocation+"-"+endLocation+"]"+"["+startDate + "-"+ endDate + "]";
        textView.setText(temp);

    }

}