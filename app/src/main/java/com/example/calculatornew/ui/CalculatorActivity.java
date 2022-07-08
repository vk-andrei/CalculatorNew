package com.example.calculatornew.ui;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculatornew.R;
import com.example.calculatornew.model.CalculatorExample;
import com.example.calculatornew.model.Operator;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView display;
    private CalculatorPresenter calculatorPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false); // not to show android keyboard

        calculatorPresenter = new CalculatorPresenter(new CalculatorExample(), this);

        Map<Integer, String> digitsMap = new HashMap<>();
        digitsMap.put(R.id.BTN_zero, "0");
        digitsMap.put(R.id.BTN_one, "1");
        digitsMap.put(R.id.BTN_two, "2");
        digitsMap.put(R.id.BTN_three, "3");
        digitsMap.put(R.id.BTN_four, "4");
        digitsMap.put(R.id.BTN_five, "5");
        digitsMap.put(R.id.BTN_six, "6");
        digitsMap.put(R.id.BTN_seven, "7");
        digitsMap.put(R.id.BTN_eight, "8");
        digitsMap.put(R.id.BTN_nine, "9");


        Map<Integer, Operator> operatorMap = new HashMap<>();
        operatorMap.put(R.id.BTN_plus, Operator.ADD);
        operatorMap.put(R.id.BTN_minus, Operator.SUB);
        operatorMap.put(R.id.BTN_mult, Operator.MULT);
        operatorMap.put(R.id.BTN_divide, Operator.DIV);


        View.OnClickListener digitsOnClick = view -> calculatorPresenter.onDigitPressed(digitsMap.get(view.getId()));

        for (Map.Entry entry : digitsMap.entrySet()) {
            findViewById((int) entry.getKey()).setOnClickListener(digitsOnClick);
        }

        View.OnClickListener operatorOnClick = view -> calculatorPresenter.onOperatorsPressed(operatorMap.get(view.getId()));

        for (Map.Entry entry : operatorMap.entrySet()) {
            findViewById((int) entry.getKey()).setOnClickListener(operatorOnClick);
        }

        findViewById(R.id.BTN_backspace).setOnClickListener(view -> calculatorPresenter.onBackspacePressed(display.getText().toString()));
        findViewById(R.id.BTN_clear).setOnClickListener(view -> calculatorPresenter.onClearPressed());
        findViewById(R.id.BTN_point).setOnClickListener(view -> calculatorPresenter.onDotPressed());
        findViewById(R.id.BTN_equal).setOnClickListener(view -> calculatorPresenter.onEqualPressed());
        findViewById(R.id.BTN_sqrt).setOnClickListener(view -> calculatorPresenter.onSqrPressed());
        findViewById(R.id.BTN_style).setOnClickListener(view -> calculatorPresenter.onStylePressed(view.getId()));


    }

    @Override
    public void showResult(String text) {
        display.setText(text);

    }
}