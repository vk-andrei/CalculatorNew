package com.example.calculatornew.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.example.calculatornew.R;

public enum Theme {

    THEME_ONE(R.style.Theme_CalculatorNew, R.string.themeOne, "themeOne"),
    THEME_TWO(R.style.Theme_CalculatorNew_V2, R.string.themeTwo, "themeTwo"),
    THEME_THREE(R.style.Theme_CalculatorNew_V3, R.string.themeThree, "ThemeThree");

    @StyleRes
    private final int themeRes;
    @StringRes
    private final int themeName;

    private final String keyTheme;

    Theme(int themeRes, int themeName, String keyTheme) {
        this.themeRes = themeRes;
        this.themeName = themeName;
        this.keyTheme = keyTheme;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getThemeName() {
        return themeName;
    }

    public String getKeyTheme() {
        return keyTheme;
    }
}