package com.myapplicationsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements Nameable {

    DatabaseHandler db;

    EditText etLogin, etPassword;
    Button bLogin, bSignUp, bCalculatrice, bTemperature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Show toolbar + the button to toggle the menu */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ((MyApp) this.getApplication()).addToolbarControls(this, drawerLayout, myToolbar, navigationView);
        /* Show toolbar + the button to toggle the menu */


        etLogin = findViewById(R.id.etLogin);
        etPassword = findViewById(R.id.etPassword);
        bLogin = findViewById(R.id.bLogin);
        bSignUp = findViewById(R.id.bSignUp);
        bCalculatrice = findViewById(R.id.bCalculatrice);
        bTemperature = findViewById(R.id.bTemperature);

        db = new DatabaseHandler(this);



        bLogin.setOnClickListener(v -> login());
        bSignUp.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivitySignUp.class)));
        bCalculatrice.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CalculatorActivity.class)));
        bTemperature.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TemperatureActivity.class)));


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
            if(clientByEmail.getPassword().equals(etPassword.getText().toString())){
                //Set current user's informations
                ((MyApp) this.getApplication()).setUserIsLoggedIn(true);
                ((MyApp) this.getApplication()).setCurrentUser(clientByEmail);

                /* set Username and email on header from sidebar */
                NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

                View headerView = navigationView.getHeaderView(0);
                TextView tvHeaderUsername = (TextView) headerView.findViewById(R.id.tvHeaderUsername);
                TextView tvHeaderUseremail = (TextView) headerView.findViewById(R.id.tvHeaderUseremail);

                tvHeaderUsername.setText(clientByEmail.getName());
                tvHeaderUseremail.setText(clientByEmail.getEmail());

                //change the login to logout
                Menu menu = navigationView.getMenu();
                MenuItem tvMenuLogin = menu.findItem(R.id.nav_login);
                tvMenuLogin.setTitle("Logout");

                /* set Username and email on header from sidebar */

               // startActivity(new Intent(MainActivity.this, MainActivityAffichage.class));
            }

            else
                Toast.makeText(MainActivity.this, "Utilisateur non trouvé", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(MainActivity.this, "Merci de remplir les champs", Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getNavName() {
        return "Login";
    }

}