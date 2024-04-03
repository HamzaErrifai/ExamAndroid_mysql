package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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

import com.google.android.material.navigation.NavigationView;

public class MainActivitySignUp extends AppCompatActivity implements Nameable {

    EditText etName2, etEmail2, etPassword2;
    Button bValider2, bAnnuler;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        /* Show toolbar + the button to toggle the menu */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ((MyApp) this.getApplication()).addToolbarControls(this, drawerLayout, myToolbar, navigationView);
        /* Show toolbar + the button to toggle the menu */

        etName2 = findViewById(R.id.etName2);
        etEmail2 = findViewById(R.id.etEmail2);
        etPassword2 = findViewById(R.id.etPassword2);
        bValider2 = findViewById(R.id.bValider2);
        bAnnuler = findViewById(R.id.bAnnuler);

        db = new DatabaseHandler(this);

        bValider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Insert: ", "Inserting ...");
                //db.addClient(new Client(1, "youness", "ykhamlichi@gmail.com", "123456"));
                //db.addClient(new Client(1, "karim", "karim2@gmail.com", "123456"));
                if(!etName2.getText().toString().isEmpty() && !etEmail2.getText().toString().isEmpty() && !etPassword2.getText().toString().isEmpty()){
                    Client client = new Client(etName2.getText().toString(), etEmail2.getText().toString(), etPassword2.getText().toString());
                    long x = db.addClient(client);
                    if(x != -1){
                        Toast.makeText(MainActivitySignUp.this, "User Created", Toast.LENGTH_SHORT).show();
                        Intent myIntent = new Intent(MainActivitySignUp.this, MainActivity.class);
                        myIntent.putExtra("email",etEmail2.getText().toString());
                        myIntent.putExtra("password",etPassword2.getText().toString());
                        startActivity(myIntent);
                        //startActivity(new Intent(MainActivitySignUp.this, MainActivity.class)); // redirect to mainActivity
                    }
                    else
                        Toast.makeText(MainActivitySignUp.this, "Your email does already exist!", Toast.LENGTH_SHORT).show();
                    Log.d("Insertion: ", "Inserting ..." + client.toString());
                }else{
                    Toast.makeText(MainActivitySignUp.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivitySignUp.this, MainActivity.class));
            }
        });
        /* set Username and email on header from sidebar */
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView tvHeaderUsername = (TextView) headerView.findViewById(R.id.tvHeaderUsername);
        TextView tvHeaderUseremail = (TextView) headerView.findViewById(R.id.tvHeaderUseremail);
        if(((MyApp) this.getApplication()).getUserIsLoggedIn()){
            tvHeaderUsername.setText(((MyApp) this.getApplication()).getCurrentUser().getName());
            tvHeaderUseremail.setText(((MyApp) this.getApplication()).getCurrentUser().getEmail());
            //change the login to logout
            Menu menu = navigationView.getMenu();
            MenuItem tvMenuLogin = menu.findItem(R.id.nav_login);
            tvMenuLogin.setTitle("Logout");
        }
        /* set Username and email on header from sidebar */

    }

    @Override
    public String getNavName() {
        return "SignUp";
    }
}