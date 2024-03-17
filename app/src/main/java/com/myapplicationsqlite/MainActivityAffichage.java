package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivityAffichage extends AppCompatActivity {

    TextView tvAffichage;
    DatabaseHandler db;
    String dataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_affichage);

        tvAffichage = findViewById(R.id.tvAffichage);

        db = new DatabaseHandler(this);

        List<Client> clientList = db.getAllClients();

        for (Client client: clientList) {
            dataList += client.toString();
        }
        tvAffichage.setText(dataList);

    }
}