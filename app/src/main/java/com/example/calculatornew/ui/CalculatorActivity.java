package com.example.calculatornew.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.calculatornew.R;

public class CalculatorActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false); // not to show android keyboard

        int[] digitsIds = new int[] {
                R.id.BTN_zero,
                R.id.BTN_one,
                R.id.BTN_two,
                R.id.BTN_three,
                R.id.BTN_four,
                R.id.BTN_five,
                R.id.BTN_six,
                R.id.BTN_seven,
                R.id.BTN_eight,
                R.id.BTN_nine,
                R.id.BTN_zero,
                R.id.BTN_zero
        };

        int[] operatorsIds = new int[] {
                R.id.BTN_plus,
                R.id.BTN_minus,
                R.id.BTN_mult,
                R.id.BTN_divide,
        };






















    }
}