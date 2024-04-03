package com.myapplicationsqlite;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MyApp extends Application {
    private String someVariable;

    public String getSomeVariable() {
        return someVariable;
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
                        if (!nameClass.getNavName().equals("Login"))
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
                }
                return false;
            }
        });
    }

    public void setSomeVariable(String someVariable) {
        this.someVariable = someVariable;
    }
}

