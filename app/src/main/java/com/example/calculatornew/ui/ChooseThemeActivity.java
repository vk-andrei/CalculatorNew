package com.example.calculatornew.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculatornew.R;
import com.example.calculatornew.model.Theme;
import com.example.calculatornew.model.ThemeReposImplementation;
import com.example.calculatornew.model.ThemeRepository;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

public class ChooseThemeActivity extends AppCompatActivity {

    public static final String EXTRA_THEME = "EXTRA_THEME";  // static???

    private ThemeRepository themeRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /** Установка ТЕМЫ: **/
        themeRepository = ThemeReposImplementation.getINSTANCE(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());
        /*********************/
        setContentView(R.layout.activity_choose_theme);

        ThemeRepository themeRepository = ThemeReposImplementation.getINSTANCE(this);
        List<Theme> themeList = themeRepository.getAllThemes();

        LinearLayout container = findViewById(R.id.container);

        // получем данные из ИНТЕНТА о текущей теме:
        Intent incomingIntent = getIntent();
        Theme selectedTheme = (Theme) incomingIntent.getSerializableExtra(EXTRA_THEME);

        for (Theme theme : themeList) {

            View itemView  = getLayoutInflater().inflate(R.layout.item_theme, container, false);

            // CHILDREN OF itemView:
            // 1
            TextView txtViewTitle = itemView.findViewById(R.id.title);
            txtViewTitle.setText(theme.getThemeName());
            // 2
            CardView cardView = itemView.findViewById(R.id.theme_card);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Toast.makeText(ChooseThemeActivity.this, theme.getThemeName(), Toast.LENGTH_SHORT).show();

                    //передадим данные в 1ю активити о текущей теме и ЗАКРОЕМ активити
                    Intent data = new Intent();
                    data.putExtra(EXTRA_THEME, theme);
                    setResult(Activity.RESULT_OK, data);
                    finish();
                }
            });

            ImageView imageChecked = itemView.findViewById(R.id.check);
            if (selectedTheme.equals(theme)) {
                imageChecked.setVisibility(View.VISIBLE);
            } else {
                imageChecked.setVisibility(View.GONE);
            }

            // Add view to container
            container.addView(itemView);

        }

    }
}