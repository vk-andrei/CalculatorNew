package com.example.calculatornew.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.calculatornew.model.Calculator;
import com.example.calculatornew.model.Operator;

public class CalculatorPresenter { //implements Parcelable {

    private final Calculator calculator;
    private final CalculatorView calculatorView;
    private String argOne = "0";
    private String argTwo = "";
    private Operator selectedOperator = null;

    public CalculatorPresenter(Calculator calculator, CalculatorView calculatorView) {
        this.calculator = calculator;
        this.calculatorView = calculatorView;
    }

    public void setData(CalculatorPresenter.Data data) {
        if (data != null) {
            argOne = data.getArgOne();
            argTwo = data.getArgTwo();
            selectedOperator = data.getSelectedOperator();
        }
    }

    public CalculatorPresenter.Data getData() {
        return new CalculatorPresenter.Data(argOne, argTwo, selectedOperator);
    }


    public void setArgOne(String argOne) {
        this.argOne = argOne;
    }

    public void setArgTwo(String argTwo) {
        this.argTwo = argTwo;
    }

    private void calc() {
        argOne = String.valueOf(calculator.perform(argOne, argTwo, selectedOperator));
        calculatorView.showResult(argOne);
        argTwo = "";
    }

    protected void updateState() {
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


    /**
     * класс для хранения инфы и передачи ее
     **/

    public static class Data implements Parcelable {

        private final String argOne;
        private final String argTwo;
        private final Operator selectedOperator;

        public Data(String argOne, String argTwo, Operator selectedOperator) {
            this.argOne = argOne;
            this.argTwo = argTwo;
            this.selectedOperator = selectedOperator;
        }

        protected Data(Parcel in) {
            argOne = in.readString();
            argTwo = in.readString();
            selectedOperator = Operator.values()[in.readInt()];
        }

        public String getArgOne() {
            return argOne;
        }

        public String getArgTwo() {
            return argTwo;
        }

        public Operator getSelectedOperator() {
            return selectedOperator;
        }

        public static final Creator<Data> CREATOR = new Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(argOne);
            parcel.writeString(argTwo);
            parcel.writeInt(selectedOperator.ordinal());
        }
    }

}
