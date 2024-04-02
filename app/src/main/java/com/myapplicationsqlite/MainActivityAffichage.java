package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

public class MainActivityAffichage extends AppCompatActivity {
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
                myIntent.putExtra("name",selectedName);
                myIntent.putExtra("email",selectedEmail);
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


    }
}