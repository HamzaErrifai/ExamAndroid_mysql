package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddClientActivity extends AppCompatActivity {
    EditText etName2, etEmail2, etPassword2;
    Button bValider2, bAnnuler;
    DatabaseHandler db;
    TextView tvSignUp;

    String mode; // add, edit
    String originalEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        etName2 = findViewById(R.id.etName2);
        etEmail2 = findViewById(R.id.etEmail2);
        etPassword2 = findViewById(R.id.etPassword2);
        bValider2 = findViewById(R.id.bValider2);
        bAnnuler = findViewById(R.id.bAnnuler);
        tvSignUp = findViewById(R.id.tvSignUp);

        mode = new String("add");
        Intent myIntent = getIntent();
        if (myIntent.getExtras() != null) {
            mode = new String("edit");
            tvSignUp.setText("Modifier un client");

            String originalName = getIntent().getStringExtra("name");
            originalEmail = getIntent().getStringExtra("email");
            Log.i("Original email: ", originalEmail);

            etName2.setText(originalName);
            etEmail2.setText(originalEmail);
            //Hide password Field
            TextView tvPassword2 = (TextView) findViewById(R.id.tvPassword2);
            tvPassword2.setVisibility(View.GONE);
            EditText etPassword2 = (EditText)findViewById(R.id.etPassword2);
            etPassword2.setVisibility(View.GONE);



        }
        db = new DatabaseHandler(this);


        bValider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etName2.getText().toString().isEmpty() && !etEmail2.getText().toString().isEmpty()) {
                    Client client = new Client(etName2.getText().toString(), etEmail2.getText().toString(), etPassword2.getText().toString());
                    //add
                    if (mode.equals("add")) {
                        long x = db.addClient(client);
                        if (x != -1) {
                            Toast.makeText(AddClientActivity.this, "Client créé", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(AddClientActivity.this, MainActivityAffichage.class)); // redirect to mainActivity
                        } else
                            Toast.makeText(AddClientActivity.this, "Ton email existe déjà!", Toast.LENGTH_LONG).show();
                    }
                    //edit
                    else {
                        db.updateClient(client, originalEmail);
                        Toast.makeText(AddClientActivity.this, "Client modifié", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(AddClientActivity.this, MainActivityAffichage.class)); // redirect to mainActivity

                    }

                } else {
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