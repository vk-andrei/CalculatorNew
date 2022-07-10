package com.example.calculatornew.model;

import java.util.List;

public interface ThemeRepository {

    void saveTheme(Theme theme);

    Theme getSavedTheme();

    List<Theme> getAllThemes();

}
