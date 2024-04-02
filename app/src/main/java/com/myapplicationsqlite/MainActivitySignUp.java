package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivitySignUp extends AppCompatActivity {

    EditText etName2, etEmail2, etPassword2;
    Button bValider2, bAnnuler;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

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

    }
}