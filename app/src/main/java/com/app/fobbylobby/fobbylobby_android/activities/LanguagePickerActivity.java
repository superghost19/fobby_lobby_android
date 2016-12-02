package com.app.fobbylobby.fobbylobby_android.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.fobbylobby.fobbylobby_android.R;

import java.util.Locale;

public class LanguagePickerActivity extends AppCompatActivity {
    public static final String ENGLISH = "en";
    public static final String CHINESE = "zh";

    private Button englishButton;
    private Button chineseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_picker);

        englishButton = (Button) findViewById(R.id.english_button);
        chineseButton = (Button) findViewById(R.id.chinese_button);

        englishButton.setOnClickListener(new EnglishButtonListener());
        chineseButton.setOnClickListener(new ChineseButtonListener());
    }

    public static boolean setLocale(Context context, String language) {
        return updateResources(context, language);
    }

    private static boolean updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return true;
    }

    public class EnglishButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setLocale(getApplicationContext(), ENGLISH);
            startActivity(new Intent(LanguagePickerActivity.this, LoginActivity.class));
        }
    }

    public class ChineseButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setLocale(getApplicationContext(), CHINESE);
            startActivity(new Intent(LanguagePickerActivity.this, LoginActivity.class));
        }
    }
}
