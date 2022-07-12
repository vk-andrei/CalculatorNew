package com.example.calculatornew.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.List;

public class ThemeReposImplementation implements ThemeRepository {

    private final String KEY_THEME = "KEY_THEME";

    // Доступ к темам (СИНГЛТОН). Этот интерфейс в одном экземпляре только. Доступ к нему через геттер
    private static ThemeRepository INSTANCE;

    private final SharedPreferences preferences;

    // Конструктор
    private ThemeReposImplementation(Context context) {
        preferences = context.getSharedPreferences("themes.xml", Context.MODE_PRIVATE);
    }
    // доступ к СИНГЛТОНУ
    public static ThemeRepository getINSTANCE(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ThemeReposImplementation(context);
        }
        return INSTANCE;
    }

    @Override
    public void saveTheme(Theme theme) {
        preferences
                .edit()
                .putString(KEY_THEME, theme.getKeyTheme())
                .apply();
    }

    @Override
    public Theme getSavedTheme() {

        String savedKey = preferences.getString(KEY_THEME, Theme.THEME_ONE.getKeyTheme());

        for (Theme theme : Theme.values()) {
            if (theme.getKeyTheme().equals(savedKey)) {
                return theme;
            }
        }
        return Theme.THEME_ONE;
    }

    @Override
    public List<Theme> getAllThemes() {
        return Arrays.asList(Theme.values());
    }
}