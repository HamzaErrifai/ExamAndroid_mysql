package com.myapplicationsqlite;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MyApp extends Application {
    private Boolean userIsLoggedIn = false;


    private Client currentUser = null;


    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }

    public Client getCurrentUser() {
        return currentUser;
    }

    public Boolean getUserIsLoggedIn() {
        return userIsLoggedIn;
    }

    public void setUserIsLoggedIn(Boolean newStatus) {
        this.userIsLoggedIn = newStatus;
    }


    public void addToolbarControls(Activity THIS, DrawerLayout drawerLayout, Toolbar myToolbar, NavigationView navigationView) {
        /* Show toolbar + the button to toggle the menu */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(THIS, drawerLayout, myToolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        /* Show toolbar + the button to toggle the menu */
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Nameable nameClass = (Nameable) THIS;
                Log.i("item :", item.toString());
                Log.i("THIS NAMECLASS", nameClass.getNavName());

                switch (item.toString()) {
                    case "Login":
                        if (!nameClass.getNavName().equals("Login")) if (!getUserIsLoggedIn())
                            startActivity(new Intent(THIS, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case "Calculatrice":
                        if (!nameClass.getNavName().equals("Calculatrice"))
                            startActivity(new Intent(THIS, CalculatorActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case "Temperature":
                        if (!nameClass.getNavName().equals("Temperature"))
                            startActivity(new Intent(THIS, TemperatureActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                    case "Afficher":
                        if (!nameClass.getNavName().equals("Afficher")) if (getUserIsLoggedIn())
                            startActivity(new Intent(THIS, MainActivityAffichage.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        else
                            Toast.makeText(THIS, "Connectez vous s'il vous plaît.", Toast.LENGTH_SHORT).show();
                        break;
                    case "Logout":
                        if (userIsLoggedIn) {
                            setUserIsLoggedIn(false);
                            setCurrentUser(null);
                        }

                        startActivity(new Intent(THIS, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        break;
                }
                return false;
            }
        });
    }


}

