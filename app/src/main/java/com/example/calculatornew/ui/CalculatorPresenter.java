package com.example.calculatornew.ui;

import com.example.calculatornew.model.Calculator;
import com.example.calculatornew.model.Operator;

import java.text.DecimalFormat;

public class CalculatorPresenter {

    private final Calculator calculator;
    private final CalculatorView calculatorView;
    private String argOne = "0";
    private String argTwo = "";
    private Operator selectedOperator = null;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.####");

    public CalculatorPresenter(Calculator calculator, CalculatorView calculatorView) {
        this.calculator = calculator;
        this.calculatorView = calculatorView;
    }

    private void calc() {
        argOne = String.valueOf(calculator.perform(argOne, argTwo, selectedOperator));
        calculatorView.showResult(argOne);
        argTwo = "";
    }

    private void updateState() {
        if (selectedOperator != null) {
            if (argTwo.equals("")) {
                calculatorView.showResult(argOne);
            } else {
                calculatorView.showResult(argTwo);
            }
        } else {
            calculatorView.showResult(argOne);
        }
    }

    public void onDigitPressed(String number) {
        if (selectedOperator != null) {
            argTwo += number;
        } else {
            if (argOne.equals("0")) {
                argOne = number;
            } else {
                argOne += number;
            }
        }
        updateState();
    }

    public void onOperatorsPressed(Operator operator) {
        if (selectedOperator != null && !argTwo.equals("")) {
            calc();
        }
        selectedOperator = operator;
    }

    public void onBackspacePressed(String textOnDisplay) {
        StringBuilder str = new StringBuilder();
        if (argTwo.equals("")) {
            if (textOnDisplay.length() > 1) {
                for (int i = 0; i < textOnDisplay.length() - 1; i++) {
                    str.append(textOnDisplay.charAt(i));
                }
                argOne = str.toString();
            } else {
                argOne = "0";
            }
        } else { // argTwo has something
            if (textOnDisplay.length() > 1) {
                for (int i = 0; i < textOnDisplay.length() - 1; i++) {
                    str.append(textOnDisplay.charAt(i));
                }
                argTwo = str.toString();
            } else {
                argOne = "0";
            }
        }
        updateState();
    }

    // IMPLEMENTATION OF DOT!!! MOTHERFUCKER!!!
    public void onDotPressed() {
        if (selectedOperator != null && !argTwo.contains(".")) {
            argTwo += ".";
            updateState();
        } else {
            if (!argOne.contains(".")) {
                argOne += ".";
                updateState();
            }
        }
    }

    public void onEqualPressed() {
        if (selectedOperator != null && !argTwo.equals("")) {
            calc();
        }
    }

    public void onClearPressed() {
        argOne = "";
        argTwo = "";
        selectedOperator = null;
        calculatorView.showResult("0");
    }

    public void onSqrPressed() {
        selectedOperator = Operator.SQRT;
        if (!argOne.equals("") || !argTwo.equals("")) {
            if (!argTwo.equals("")) {
                argOne = argTwo;
            }
            argOne = String.valueOf(calculator.perform(argOne, "0", selectedOperator));
            calculatorView.showResult(argOne);
            argTwo = "";
        }
    }

    public void onStylePressed(int styleId) {
    }
}
