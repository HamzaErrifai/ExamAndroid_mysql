package com.myapplicationsqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, Nameable {

    //Calculation Variables
    private Double ans = null;
    private String ansOperation = null;
    private boolean isEqualActive = false;
    private boolean inNegative = false;

    private LinearLayout linearLayout;

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnPoint;
    private Button btnPlus, btnMinus, btnMultiply, btnDivide, btnEqual, btnPercent, btnNegate;
    private Button clearBtn, btnDeleteLastNumb;
    private TextView smallTv, bigTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        /* Show toolbar + the button to toggle the menu */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ((MyApp) this.getApplication()).addToolbarControls(this, drawerLayout, myToolbar, navigationView);
        /* Show toolbar + the button to toggle the menu */

        //Defining our buttons
        btn0 = (Button) findViewById(R.id.btn0);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btnPoint = (Button) findViewById(R.id.btnPoint);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        btnDeleteLastNumb = (Button) findViewById(R.id.btnDeleteLastNumb);
        btnPlus = (Button) findViewById(R.id.btnPlus);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnMultiply = (Button) findViewById(R.id.btnMultiply);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        btnEqual = (Button) findViewById(R.id.btnEqual);
        btnPercent = (Button) findViewById(R.id.btnPercent);
        btnNegate = (Button) findViewById(R.id.btnNegate);

        //Defining our textViews
        smallTv = (TextView) findViewById(R.id.smallTv);
        bigTv = (TextView) findViewById(R.id.bigTv);

        //setting the default function
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnPoint.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        btnDeleteLastNumb.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnMultiply.setOnClickListener(this);
        btnDivide.setOnClickListener(this);
        btnEqual.setOnClickListener(this);
        btnPercent.setOnClickListener(this);
        btnNegate.setOnClickListener(this);

    }

    /**
     * This function calculate two numbers and sets the result to ans
     *
     * @param operation   the Arithmetic Operation
     * @param nextOperand the operand
     */
    private void calculate(String operation, String nextOperand) {
        if (ans == null) {
            ans = Double.parseDouble(nextOperand);
        } else
            switch (ansOperation) {
                case "+":
                    ans += Double.parseDouble(nextOperand);
                    break;
                case "-":
                    ans -= Double.parseDouble(nextOperand);
                    break;
                case "x":
                    ans *= Double.parseDouble(nextOperand);
                    break;
                case "/":
                    try {
                        ans /= Double.parseDouble(nextOperand);
                    } catch (ArithmeticException e) {
                        bigTv.setText("You can't divide by Zero");
                    }
                    break;

            }
        if (!operation.equals("="))
            ansOperation = operation;
    }


    public void onClick(View v) {
        //here the order of the ifs is important
        if (v.getTag().equals("negate")) {//add/remove the negation
            if (!bigTv.getText().toString().equals("0")) {
                if (!bigTv.getText().toString().contains("-"))
                    bigTv.setText("-" + bigTv.getText().toString());
                else bigTv.setText(bigTv.getText().toString().replace("-", ""));
            }
        } else if (v.getTag().equals("del")) { //deletes last digit of the bigTv
            if (isEqualActive) clearTvs();
            int len = bigTv.getText().toString().length();
            if (len > 1)
                bigTv.setText(bigTv.getText().toString().substring(0, len - 1));
            else
                bigTv.setText("0");

        } else if (v.getTag().equals("clear")) { //clears the screen
            clearTvs();

        } else if (v.getTag().toString().equals("%")) {
            smallTv.setText(String.format("%s%s", bigTv.getText(), "%"));
            bigTv.setText(String.format("%s", Double.parseDouble(bigTv.getText().toString()) / 100));

        } else if (v.getTag().equals("+") || v.getTag().equals("-") || v.getTag().equals("x") || v.getTag().equals("/")) { //Arithmetic operations
            //ans+
            calculate(String.format("%s", v.getTag()), String.format("%s", bigTv.getText()));
            smallTv.setText(String.format("%s%s", ans, v.getTag()));
            bigTv.setText("0");
        } else if (v.getTag().equals("=")) { //equal
            if (ans != null) {
                //a+b=
                smallTv.setText(String.format("%s%s%s%s", ans, ansOperation, bigTv.getText(), "="));
                calculate(String.format("%s", v.getTag()), String.format("%s", bigTv.getText()));
                bigTv.setText(String.format("%s", ans));
                isEqualActive = true;
            }
        } else { //sets the value
            if (isEqualActive) clearTvs();
            if (v.getTag().equals(".") && bigTv.getText().toString().contains(".")) {
                //if it contains the "." don't repeat it
            } else if (bigTv.getText().equals("0")) {
                bigTv.setText(v.getTag().toString());
            } else {
                bigTv.setText(String.format("%s%s", bigTv.getText(), v.getTag().toString()));
            }
        }

    }

    private void clearTvs() {
        bigTv.setText("0");
        smallTv.setText("");
        ans = null;
        isEqualActive = false;
    }

    @Override
    public String getNavName() {
        return "Calculatrice";
    }
}