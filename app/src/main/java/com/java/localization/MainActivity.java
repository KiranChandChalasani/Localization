package com.java.localization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button changeButton;
    private TextView localizedTextView;
    static String localvalue = "en";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        changeButton = findViewById(R.id.button);
        localizedTextView = findViewById(R.id.textView);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languageSelection();
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    public  void languageSelection(){
        Resources res = getResources();
        Locale locale;
        if (localvalue.equalsIgnoreCase("en")) {
            localvalue = "te";
            locale = new Locale("te");
        } else {
            localvalue = "en";
            locale = new Locale("en");
        }
        Locale.setDefault(locale);
        Configuration config = res.getConfiguration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            config.setLocale(locale);
            getApplicationContext().createConfigurationContext(config);
        }
        DisplayMetrics dm = res.getDisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            config.setLocales(new LocaleList(locale));
        } else {
            config.locale = locale;
        }
        res.updateConfiguration(config, dm);
    }

}