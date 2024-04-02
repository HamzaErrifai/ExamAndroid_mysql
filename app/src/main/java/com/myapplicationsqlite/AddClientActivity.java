package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddClientActivity extends AppCompatActivity {
    EditText etName2, etEmail2, etPassword2;
    Button bValider2, bAnnuler;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        etName2 = findViewById(R.id.etName2);
        etEmail2 = findViewById(R.id.etEmail2);
        etPassword2 = findViewById(R.id.etPassword2);
        bValider2 = findViewById(R.id.bValider2);
        bAnnuler = findViewById(R.id.bAnnuler);

        db = new DatabaseHandler(this);

        bValider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!etName2.getText().toString().isEmpty() && !etEmail2.getText().toString().isEmpty() && !etPassword2.getText().toString().isEmpty()){
                    Client client = new Client(etName2.getText().toString(), etEmail2.getText().toString(), etPassword2.getText().toString());
                    long x = db.addClient(client);
                    if(x != -1){
                        Toast.makeText(AddClientActivity.this, "Client créé", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddClientActivity.this, MainActivityAffichage.class)); // redirect to mainActivity
                    }
                    else
                        Toast.makeText(AddClientActivity.this, "Ton email existe déjà!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(AddClientActivity.this, "Données invalides", Toast.LENGTH_LONG).show();
                }

            }
        });

        bAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(AddClientActivity.this, MainActivityAffichage.class));
            }
        });
    }
}