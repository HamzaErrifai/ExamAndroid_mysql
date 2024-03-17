package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import java.util.List;

public class MainActivityAffichage extends AppCompatActivity {
    TextView tvAffichage;
    ListView lvUsers;

    DatabaseHandler db;
    String dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_affichage);
//        String[] names = {"Hamza", "Ahmed", "Imad"};
//        String[] emails = {"Hamza@gmail.com", "Ahmed@gmail.com", "Imad@gmail.com"};

        lvUsers = findViewById(R.id.list_users);

        db = new DatabaseHandler(this);
        List<Client> clientList = db.getAllClients();

        String[] names=new String[clientList.size()];
        String[] emails=new String[clientList.size()];

        for(int i = 0; i<clientList.size(); i++){
            names[i]=clientList.get(i).getName();
            emails[i]=clientList.get(i).getEmail();
        }


        CustomBaseAdapter customBaseAdapter = new CustomBaseAdapter(getApplicationContext(), names, emails);
        lvUsers.setAdapter(customBaseAdapter);


    }
}