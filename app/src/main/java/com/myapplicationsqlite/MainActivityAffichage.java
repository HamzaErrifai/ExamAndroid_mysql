package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivityAffichage extends AppCompatActivity implements Nameable {
    TextView tvAffichage;
    ListView lvUsers;

    DatabaseHandler db;
    String dataList;
    Button bAjouter, bModifier, bSupprimer;
    int selectedClientIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_affichage);

        /* Show toolbar + the button to toggle the menu */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ((MyApp) this.getApplication()).addToolbarControls(this, drawerLayout, myToolbar, navigationView);
        /* Show toolbar + the button to toggle the menu */

        if (!((MyApp) this.getApplication()).getUserIsLoggedIn()) { // if the user is not logged in redirect to the mainActivity
            startActivity(new Intent(MainActivityAffichage.this, MainActivity.class));
        }


        lvUsers = findViewById(R.id.list_users);
        bAjouter = findViewById(R.id.bAjouter);
        bModifier = findViewById(R.id.bModifier);
        bSupprimer = findViewById(R.id.bSupprimer);


        db = new DatabaseHandler(this);
        List<Client> clientList = db.getAllClients();

        String[] names = new String[clientList.size()];
        String[] emails = new String[clientList.size()];

        for (int i = 0; i < clientList.size(); i++) {
            names[i] = clientList.get(i).getName();
            emails[i] = clientList.get(i).getEmail();
        }


        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), names, emails);
        lvUsers.setAdapter(customBaseAdapter);
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedClientIndex = position;
                Log.i("lvUsers selected item", String.valueOf(position));
            }
        });
        bAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivityAffichage.this, AddClientActivity.class));
            }
        });
        bModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedEmail = emails[selectedClientIndex];
                String selectedName = names[selectedClientIndex];
                Intent myIntent = new Intent(MainActivityAffichage.this, AddClientActivity.class);
                myIntent.putExtra("name", selectedName);
                myIntent.putExtra("email", selectedEmail);
                startActivity(myIntent);
            }
        });
        bSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedEmail = emails[selectedClientIndex];
                Log.i("selectedClientIndex selected item", selectedEmail);
                new AlertDialog.Builder(MainActivityAffichage.this)
                        .setTitle("Alerte de suppression")
                        .setMessage("êtes vous sure de supprimer " + selectedEmail + " ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                db.deleteClient(selectedEmail);
                                Toast.makeText(MainActivityAffichage.this, selectedEmail + " a été supprimé", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });

        /* set Username and email on header from sidebar */
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView tvHeaderUsername = (TextView) headerView.findViewById(R.id.tvHeaderUsername);
        TextView tvHeaderUseremail = (TextView) headerView.findViewById(R.id.tvHeaderUseremail);
        tvHeaderUsername.setText(((MyApp) this.getApplication()).getCurrentUser().getName());
        tvHeaderUseremail.setText(((MyApp) this.getApplication()).getCurrentUser().getEmail());
        //change the login to logout
        Menu menu = navigationView.getMenu();
        MenuItem tvMenuLogin = menu.findItem(R.id.nav_login);
        tvMenuLogin.setTitle("Logout");
        /* set Username and email on header from sidebar */


    }

    @Override
    public String getNavName() {
        return "Afficher";
    }
}