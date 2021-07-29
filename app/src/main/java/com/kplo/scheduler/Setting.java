package com.kplo.scheduler;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.Menu;
import android.view.MenuInflater;
import android.content.Context;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Build;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class Setting extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    LinearLayout layout;
    Context context = this;

    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    private RadioGroup radioGroup;
    private SharedPreferences appData;
    private SharedPreferences.Editor editor;
//
//    private void load() {
//        String alarmSetting = appData.getString("ALARM_SETTING", "");
//        switch (alarmSetting) {
//            case "SOUND":
//                button1.setChecked(true);
//                break;
//            case "VIBRATE":
//                button2.setChecked(true);
//                break;
//            case "MUTE":
//                button3.setChecked(true);
//                break;
//        }
//        String modeSetting = appData.getString("MODE_SETTING","");
//        switch (modeSetting){
//            case "LIGHT":
//                radioGroup.check(R.id.idRBLight);
//                break;
//            case "DARK":
//                radioGroup.check(R.id.idRBDark);
//                break;
//            case "DEFAULT":
//                radioGroup.check(R.id.idRBDefault);
//                break;
//        }
//    }
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.setting);
//        setToolbar();
//
//        Button alarm = (Button) findViewById(R.id.alarm);
//        alarm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),alarm.class);
//                startActivity(intent);
//            }
//        });
//
//        appData = getSharedPreferences("appData", MODE_PRIVATE);
//        editor = appData.edit();
//
//
//        radioGroup = findViewById(R.id.idRGroup);
//
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//
//                switch (checkedId){
//                    case R.id.idRBLight:
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                        editor.putString("MODE_SETTING","LIGHT");
//                        editor.commit();
//                        break;
//                    case R.id.idRBDark:
//                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                        editor.putString("MODE_SETTING","DARK");
//                        editor.commit();
//                        break;
//                    case R.id.idRBDefault:
//                        // 안드로이드 10 이상
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
//                            editor.putString("MODE_SETTING","DEFAULT");
//                            editor.commit();
//                        }
//                        // 안드로이드 10 미만
//                        else {
//                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
//                        }
//                        break;
//                }
//            }
//        });
//
//        button1 = (RadioButton) findViewById(R.id.button1);
//        button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
//                ringtone.play();
//                editor.putString("ALARM_SETTING","SOUND");
//                editor.commit();
//            }
//        });
//
//        button2 = (RadioButton) findViewById(R.id.button2);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
//                if(Build.VERSION.SDK_INT >=26){
//                    vibrator.vibrate(VibrationEffect.createOneShot(1000,10));
//                }else{
//                    vibrator.vibrate(1000);
//                }
//                editor.putString("ALARM_SETTING","VIBRATE");
//                editor.commit();
//            }
//        });
//
//        button3 = (RadioButton) findViewById(R.id.button3);
//        button3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
//                ringtone.stop();
//                editor.putString("ALARM_SETTING","MUTE");
//                editor.commit();
//            }
//        });
//        load();
//    }

}

