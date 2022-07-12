package com.example.calculatornew.ui;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculatornew.R;
import com.example.calculatornew.model.CalculatorExample;
import com.example.calculatornew.model.Operator;
import com.example.calculatornew.model.Theme;
import com.example.calculatornew.model.ThemeReposImplementation;
import com.example.calculatornew.model.ThemeRepository;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CalculatorActivity extends AppCompatActivity implements CalculatorView {

    private TextView display;
    private CalculatorPresenter calculatorPresenter;

    private ThemeRepository themeRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /** Установка ТЕМЫ: **/
     /*   SharedPreferences preferences = getSharedPreferences("theme.xml", Context.MODE_PRIVATE);
        int theme = preferences.getInt("key_theme", R.style.Theme_CalculatorNew);
        this.setTheme(theme);*/
        themeRepository = ThemeReposImplementation.getINSTANCE(this);
        setTheme(themeRepository.getSavedTheme().getThemeRes());
        /*********************/

        setContentView(R.layout.activity_calculator);

        display = findViewById(R.id.display);
        display.setShowSoftInputOnFocus(false); // not to show android keyboard

        //final MediaPlayer mediaPlayerBTNs = MediaPlayer.create(this, R.raw.btn_click);

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

        View.OnClickListener digitsOnClick = view -> {
            //mediaPlayerBTNs.start();
            calculatorPresenter.onDigitPressed(digitsMap.get(view.getId()));
        };

        for (Map.Entry entry : digitsMap.entrySet()) {
            findViewById((int) entry.getKey()).setOnClickListener(digitsOnClick);
        }

        View.OnClickListener operatorOnClick = view -> {
            //mediaPlayerBTNs.start();
            calculatorPresenter.onOperatorsPressed(operatorMap.get(view.getId()));
        };

        for (Map.Entry entry : operatorMap.entrySet()) {
            findViewById((int) entry.getKey()).setOnClickListener(operatorOnClick);
        }

        findViewById(R.id.BTN_backspace).setOnClickListener(view -> calculatorPresenter.onBackspacePressed(display.getText().toString()));
        findViewById(R.id.BTN_clear).setOnClickListener(view -> calculatorPresenter.onClearPressed());
        findViewById(R.id.BTN_point).setOnClickListener(view -> calculatorPresenter.onDotPressed());
        findViewById(R.id.BTN_equal).setOnClickListener(view -> calculatorPresenter.onEqualPressed());
        findViewById(R.id.BTN_sqrt).setOnClickListener(view -> calculatorPresenter.onSqrPressed());


        // TODO РАЗОБРАТЬСЯ С ЭТИМ!!!!!
        // themeLauncher умеет ЗАПУСКАТЬ АКТИВТИ (и он теперь будет запускаться не просто, а с тем, чтобы
        // следить за результатом. Когда ВТОРАЯ активити закроется ВЫПОЛНЕНИЕ прилетит СЮДА на эту строчку
        // с каким-то РЕЗУЛЬТАТОМ
        ActivityResultLauncher<Intent> themeLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent intent = result.getData();

                    Theme selectedTheme = (Theme) intent.getSerializableExtra(ChooseThemeActivity.EXTRA_THEME);

                    themeRepository.saveTheme(selectedTheme);
                    recreate();
                }
            }
        });

        findViewById(R.id.BTN_style).setOnClickListener(view -> {
            Intent intent = new Intent(CalculatorActivity.this, ChooseThemeActivity.class);
            // передадим в этом интене инфо о теме
            intent.putExtra(ChooseThemeActivity.EXTRA_THEME, themeRepository.getSavedTheme());
            //startActivity(intent);
            themeLauncher.launch(intent);
        });
    }

    @Override
    public void showResult(String text) {
        display.setText(text);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        calculatorPresenter.setData(savedInstanceState.getParcelable("PARSE"));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("PARSE", calculatorPresenter.getData());

    }
}
//        calculatorPresenter.setArgOne(savedInstanceState.getString("ARG_ONE"));
//        calculatorPresenter.setArgTwo(savedInstanceState.getString("ARG_TWO"));
//        calculatorPresenter.updateState();

//        outState.putString("ARG_ONE", calculatorPresenter.getArgOne());
//        outState.putString("ARG_TWO", calculatorPresenter.getArgTwo());
//        outState.put("SELECTED_OPERATOR", calculatorPresenter.getSelectedOperator());