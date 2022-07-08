package com.example.calculatornew.model;

import java.text.DecimalFormat;

public class CalculatorExample implements Calculator {
    private final DecimalFormat formatter = new DecimalFormat("#.###");

    @Override
    public double perform(String arg1, String arg2, Operator operator) {
        double firstArg = Double.parseDouble(arg1);
        double secondArg = Double.parseDouble(arg2);

        switch (operator) {
            case ADD:
                return Double.parseDouble(formatter.format(firstArg + secondArg));
            case SUB:
                return Double.parseDouble(formatter.format(firstArg - secondArg));
            case MULT:
                return Double.parseDouble(formatter.format(firstArg * secondArg));
            case DIV:
                return Double.parseDouble(formatter.format(firstArg / secondArg));
            case SQRT:
                return Double.parseDouble(formatter.format(Math.sqrt(firstArg)));
        }
        return 0.0;
    }
}