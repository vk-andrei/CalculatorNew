package com.example.calculatornew.model;

public class CalculatorExample implements Calculator {

    @Override
    public double perform(double arg1, double arg2, Operator operator) {
        switch (operator) {
            case ADD:
                return arg1 + arg2;
            case SUB:
                return arg1 - arg2;
            case MULT:
                return arg1 * arg2;
            case DIV:
                return arg1 / arg2;
        }
        return 0.0;
    }
}