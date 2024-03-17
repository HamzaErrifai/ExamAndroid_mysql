package com.myapplicationsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TemperatureActivity extends AppCompatActivity {

    private RadioGroup rgInput, rgOutput;
    private RadioButton rbInput, rbOutput;
    private Button bConvert;
    private EditText inputTemp, outputTemp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);

        rgInput = (RadioGroup) findViewById(R.id.rg_input);
        rgOutput = (RadioGroup) findViewById(R.id.rg_output);
        inputTemp = findViewById(R.id.inputTemp);
        outputTemp = findViewById(R.id.outputTemp);
        //By default it is converting from celsius to celsius
        rbInput = findViewById(R.id.inputCelsius);
        rbOutput = findViewById(R.id.outputCelsius);

        bConvert = findViewById(R.id.bConvert);


        bConvert.setOnClickListener(new View.OnClickListener() {
            String from = "Celsius";
            String to = "Celsius";

            @Override
            public void onClick(View v) {
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
        });

    }

    private double convertAndShow(String from, String to) {
        double inputVal = Double.parseDouble(inputTemp.getText().toString());
        double outputVal = 0;
        if (from != to) {
            if(from =="Celsius"){
                switch (to){
                    case "Kelvin":
                        outputTemp.setText(String.format("%,.2f",TemperatureConverter.celsiusToKelvin(inputVal)));
                        break;
                    case "Farenheit":
                        outputTemp.setText(String.format("%,.2f",TemperatureConverter.celsiusToFarenheit(inputVal)));
                        break;
                }
            } else if (from =="Farenheit") {
                switch (to){
                    case "Celsius":
                        outputTemp.setText(String.format("%,.2f",TemperatureConverter.fahrenheitToCelsius(inputVal)));
                        break;
                    case "Kelvin":
                        outputTemp.setText(String.format("%,.2f",TemperatureConverter.fahrenheitToKelvin(inputVal)));
                        break;
                }
            } else if (from == "Kelvin") {
                switch (to){
                    case "Celsius":
                        outputTemp.setText(String.format("%,.2f",TemperatureConverter.kelvinToCelsius(inputVal)));
                        break;
                    case "Farenheit":
                        outputTemp.setText(String.format("%,.2f",TemperatureConverter.kelvinToFarenheit(inputVal)));
                        break;
                }
            }
        }else {
            outputTemp.setText(Double.toString(inputVal));

        }


        return 0;
    }
}