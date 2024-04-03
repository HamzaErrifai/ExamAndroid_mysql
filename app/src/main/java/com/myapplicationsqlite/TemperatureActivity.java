package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.navigation.NavigationView;

public class TemperatureActivity extends AppCompatActivity implements Nameable {

    private RadioGroup rgInput, rgOutput;
    private RadioButton rbInput, rbOutput;
    private Button bConvert;
    private EditText inputTemp, outputTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        /* Show toolbar + the button to toggle the menu */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ((MyApp) this.getApplication()).addToolbarControls(this, drawerLayout, myToolbar, navigationView);
        /* Show toolbar + the button to toggle the menu */

        rgInput = (RadioGroup) findViewById(R.id.rg_input);
        rgOutput = (RadioGroup) findViewById(R.id.rg_output);
        inputTemp = findViewById(R.id.inputTemp);
        outputTemp = findViewById(R.id.outputTemp);

        rbInput = findViewById(R.id.inputCelsius);
        rbOutput = findViewById(R.id.outputCelsius);
        bConvert = findViewById(R.id.bConvert);

        //By default it is converting from celsius to Farenheit
        verifyAndShow();
        inputTemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String input = s.toString();
                if (!input.isEmpty())
                    verifyAndShow();

            }
        });
        rgInput.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                verifyAndShow();
            }
        });
        rgOutput.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                verifyAndShow();
            }
        });

        bConvert.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                verifyAndShow();
            }
        });

    }

    private void verifyAndShow() {
        String from = "Celsius";
        String to = "Farenheit";
        //get checked input
        int checkedInputId = rgInput.getCheckedRadioButtonId();
        if (checkedInputId == R.id.inputFarenheit) {
            from = "Farenheit";
        } else if (checkedInputId == R.id.inputKelvin) {
            from = "Kelvin";
        } else {
            from = "Celsius";
        }
        //get checked output
        int checkedOutputId = rgOutput.getCheckedRadioButtonId();
        if (checkedOutputId == R.id.outputFarenheit) {
            to = "Farenheit";
        } else if (checkedOutputId == R.id.outputKelvin) {
            to = "Kelvin";
        } else {
            to = "Celsius";
        }

        convertAndShow(from, to);
    }

    private void convertAndShow(String from, String to) {
        double inputVal = 0;
        if (!inputTemp.getText().toString().isEmpty())
            inputVal = Double.parseDouble(inputTemp.getText().toString());
        System.out.println("inputVal: " + inputVal);
        if (from != to) {
            if (from == "Celsius") {
                switch (to) {
                    case "Kelvin":
                        System.out.println("INSIDE : From: " + from + " to: " + to);
                        outputTemp.setText(String.format("%,.2f", TemperatureConverter.celsiusToKelvin(inputVal)));
                        break;
                    case "Farenheit":
                        outputTemp.setText(String.format("%,.2f", TemperatureConverter.celsiusToFarenheit(inputVal)));
                        break;
                }
            } else if (from == "Farenheit") {
                switch (to) {
                    case "Celsius":
                        outputTemp.setText(String.format("%,.2f", TemperatureConverter.fahrenheitToCelsius(inputVal)));
                        break;
                    case "Kelvin":
                        outputTemp.setText(String.format("%,.2f", TemperatureConverter.fahrenheitToKelvin(inputVal)));
                        break;
                }
            } else if (from == "Kelvin") {
                switch (to) {
                    case "Celsius":
                        outputTemp.setText(String.format("%,.2f", TemperatureConverter.kelvinToCelsius(inputVal)));
                        break;
                    case "Farenheit":
                        outputTemp.setText(String.format("%,.2f", TemperatureConverter.kelvinToFarenheit(inputVal)));
                        break;
                }
            }
        } else {
            outputTemp.setText(Double.toString(inputVal));

        }

    }

    @Override
    public String getNavName() {
        return "Temperature";
    }
}