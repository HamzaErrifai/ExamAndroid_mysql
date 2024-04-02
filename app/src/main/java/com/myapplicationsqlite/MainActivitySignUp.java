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
    Button bValider2;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sign_up);

        etName2 = findViewById(R.id.etName2);
        etEmail2 = findViewById(R.id.etEmail2);
        etPassword2 = findViewById(R.id.etPassword2);
        bValider2 = findViewById(R.id.bValider2);

        db = new DatabaseHandler(this);

        bValider2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Insert: ", "Inserting ...");
                //db.addClient(new Client(1, "youness", "ykhamlichi@gmail.com", "123456"));
                //db.addClient(new Client(1, "karim", "karim2@gmail.com", "123456"));
                if(!etName2.getText().toString().isEmpty() && !etEmail2.getText().toString().isEmpty() && !etPassword2.getText().toString().isEmpty()){
                    Client client = new Client(etName2.getText().toString(), etEmail2.getText().toString(), etPassword2.getText().toString());
                    db.addClient(client);
                    Log.d("Insertion: ", "Inserting ..." + client.toString());
                }else{
                    Toast.makeText(MainActivitySignUp.this, "Invalid input", Toast.LENGTH_LONG).show();
                }

            }
        });



    }
}