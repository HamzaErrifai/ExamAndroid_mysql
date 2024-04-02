package com.myapplicationsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity /*implements NavigationView.OnNavigationItemSelectedListener*/ {
    private DrawerLayout drawerLayout;
    DatabaseHandler db;
    EditText etLogin, etPassword;
    Button bLogin, bSignUp, bCalculatrice, bTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolbar);
//
//        drawerLayout = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, myToolbar, R.string.open_nav, R.string.close_nav);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        if (savedInstanceState == null) {
//           getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatriceFragment()).commit();
//           //navigationView.setCheckedItem(R.id.nav_calculatrice);
//        }


        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        bSignUp = findViewById(R.id.bSignUp);
        bCalculatrice = findViewById(R.id.bCalculatrice);
        bTemperature = findViewById(R.id.bTemperature);

        db = new DatabaseHandler(this);



        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivitySignUp.class));
            }
        });
        bCalculatrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CalculatorActivity.class));
            }
        });
        bTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TemperatureActivity.class));
            }
        });


        Intent myIntent = getIntent();
        if(myIntent.getExtras()!=null){
            String emailFromSignUp = myIntent.getStringExtra("email");
            String passwordFromSignUp= myIntent.getStringExtra("password");
            etLogin.setText(emailFromSignUp);
            etPassword.setText(passwordFromSignUp);
            login();
        }
        /*For debuging purposes*/
//        etLogin.setText("hamza@gmail.com");
//        etPassword.setText("12345");
//        login();
        /*TODO: delete the 4 lines on top of this line*/
    }

    private void login(){
        if(!etLogin.getText().toString().isEmpty() && !etPassword.getText().toString().isEmpty()){
            Client clientByEmail = db.getClient(etLogin.getText().toString());
            if(clientByEmail.getPassword().equals(etPassword.getText().toString()))
                startActivity(new Intent(MainActivity.this, MainActivityAffichage.class));
            else
                Toast.makeText(MainActivity.this, "Utilisateur non trouvé", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(MainActivity.this, "Merci de remplir les champs", Toast.LENGTH_LONG).show();
    }

//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//        if (item.getItemId() == R.id.nav_calculatrice)
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatriceFragment()).commit();
//        else if (item.getItemId() == R.id.nav_temperature)
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new TemperatureFragment()).commit();
//        else if (item.getItemId() == R.id.nav_afficher)
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AffichageFragment()).commit();
//        else
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CalculatriceFragment()).commit();
//        drawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
}