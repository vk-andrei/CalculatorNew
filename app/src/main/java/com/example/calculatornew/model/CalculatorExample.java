package com.example.calculatornew.model;

import android.util.Log;

import java.text.DecimalFormat;

public class CalculatorExample implements Calculator {
    private final DecimalFormat formatter = new DecimalFormat("0.####");

    @Override
    public double perform(String arg1, String arg2, Operator operator) {
        double firstArg = Double.parseDouble(arg1);
        double secondArg = Double.parseDouble(arg2);

        switch (operator) {
            case ADD:
                Log.d("TAG", "1: " + firstArg + " 2: " + secondArg);
                Log.d("TAG", "1 + 2: " + (firstArg + secondArg));
                Log.d("TAG", "formatter.format(1 + 2): " + formatter.format(firstArg + +secondArg));
                Log.d("TAG", "Double.parseDouble(formatter.format(1 + 2)): " + Double.parseDouble(formatter.format(firstArg + secondArg)));
                String str = formatter.format(firstArg + secondArg);
                String strNew = str.replace(",", ".");
                Log.d("TAG", "str: " + str);
                Log.d("TAG", "strNew: " + strNew);

                return Double.parseDouble(strNew);
            case SUB:
                return Double.parseDouble(formatter.format(firstArg - secondArg));
            case MULT:
                return Double.parseDouble(formatter.format(firstArg * secondArg));
            case DIV:
                return Double.parseDouble(formatter.format(firstArg / secondArg));
            case SQRT:
                return Double.parseDouble(formatter.format(Math.sqrt(firstArg)));
            case NONE:
                return 0.0;
        }
        return 0.0;
    }
}