package com.example.aeronav;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class RegisterActivity extends MainActivity {
    TextInputEditText editTextEmail, editTextPassword;
    private EditText signupEmail, signupPassword;
    private FirebaseAuth auth;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;
    Toolbar toolbar;

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(getApplicationContext(),SurveyActivity.class));
            finish();
        }
    }
    //Load Register Page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.drawer_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Authorization Hooks
        auth = FirebaseAuth.getInstance();
        Button signupButton = findViewById(R.id.btn_signup);
        signupEmail = findViewById(R.id.email);
        signupPassword = findViewById(R.id.password);
        TextView loginRedirectText = findViewById(R.id.registerRedirectText);

        // Navigation Hooks
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Toolbar toggle functionality
        NavigationSetup();

        //Button Listener Functions
        signupButton.setOnClickListener(v -> {
            String email = signupEmail.getText().toString().trim();
            String pass = String.valueOf(signupPassword.getText());
            if (email.isEmpty()) signupEmail.setError("Email cannot be empty");
            if (pass.isEmpty()) {signupPassword.setError("Password Cannot be empty");}
            auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
                if(task.isSuccessful()) {
                    FirebaseUser user= auth.getCurrentUser();
                    Toast.makeText(RegisterActivity.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                } else {
                    Toast.makeText(RegisterActivity.this, "Signup Failed" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
        loginRedirectText.setOnClickListener(view -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));



    }

    private void NavigationSetup() {
        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
    }
}