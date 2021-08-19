package com.kplo.scheduler;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Setting extends AppCompatActivity {

    RadioButton btn_volume;
    RadioButton btn_vibrate;
    RadioButton btn_mute;
    ImageView btn_backspace;

    Button btn_set_babyPink, btn_set_lightRed, btn_set_red,
            btn_set_lightYellow, btn_set_yellow, btn_set_orange;
    Button btn_set_lightGreen, btn_set_green, btn_set_darkGreen,
            btn_set_sprayBlue, btn_set_skyBlue, btn_set_lightBlue;
    Button btn_set_light_royal_blue, btn_set_royalBlue, btn_set_blue,
            btn_set_pink, btn_set_purple, btn_set_darkPurple;


    private RadioGroup radioGroup;
    private SharedPreferences appData;
    private SharedPreferences.Editor editor;

    private void load() {
        String alarmSetting = appData.getString("ALARM_SETTING", "");
        switch (alarmSetting) {
            case "SOUND":
                btn_volume.setChecked(true);
                break;
            case "VIBRATE":
                btn_vibrate.setChecked(true);
                break;
            case "MUTE":
                btn_mute.setChecked(true);
                break;
        }
        String modeSetting = appData.getString("MODE_SETTING", "");
        switch (modeSetting) {
            case "LIGHT":
                radioGroup.check(R.id.btn_light);
                break;
            case "DARK":
                radioGroup.check(R.id.btn_dark);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        appData = getSharedPreferences("appData", MODE_PRIVATE);
        editor = appData.edit();

        btn_volume = findViewById(R.id.btn_volume);
        btn_vibrate = findViewById(R.id.btn_vibrate);
        btn_mute = findViewById(R.id.btn_mute);
        btn_backspace = findViewById(R.id.btn_backspace);

        btn_set_babyPink = findViewById(R.id.btn_set_babyPink);
        btn_set_lightRed = findViewById(R.id.btn_set_lightRed);
        btn_set_red = findViewById(R.id.btn_set_red);
        btn_set_lightYellow = findViewById(R.id.btn_set_lightYellow);
        btn_set_yellow = findViewById(R.id.btn_set_yellow);
        btn_set_orange = findViewById(R.id.btn_set_orange);
        btn_set_lightGreen = findViewById(R.id.btn_set_lightGreen);
        btn_set_green = findViewById(R.id.btn_set_green);
        btn_set_darkGreen = findViewById(R.id.btn_set_darkGreen);
        btn_set_sprayBlue = findViewById(R.id.btn_set_sprayBlue);
        btn_set_skyBlue = findViewById(R.id.btn_set_skyBlue);
        btn_set_lightBlue = findViewById(R.id.btn_set_lightBlue);
        btn_set_light_royal_blue = findViewById(R.id.btn_set_light_royal_blue);
        btn_set_royalBlue = findViewById(R.id.btn_set_royalBlue);
        btn_set_blue = findViewById(R.id.btn_set_blue);
        btn_set_pink = findViewById(R.id.btn_set_pink);
        btn_set_purple = findViewById(R.id.btn_set_purple);
        btn_set_darkPurple = findViewById(R.id.btn_set_darkPurple);

        radioGroup = findViewById(R.id.group_mode);


        btn_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // [테마 설정] - 라디오 그룹 클릭 리스너
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.btn_light:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        editor.putString("MODE_SETTING", "LIGHT");
                        editor.commit();
                        break;
                    case R.id.btn_dark:
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        editor.putString("MODE_SETTING", "DARK");
                        editor.commit();
                        break;
                }
            }
        });

        // [알림 설정] 소리 버튼 클릭 리스너
        btn_volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
                ringtone.play();
                editor.putString("ALARM_SETTING", "SOUND");
                editor.commit();
            }
        });


        // [알림 설정] 진동 버튼 클릭 리스너
        btn_vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                if (Build.VERSION.SDK_INT >= 26) {
                    vibrator.vibrate(VibrationEffect.createOneShot(1000, 10));
                } else {
                    vibrator.vibrate(1000);
                }
                editor.putString("ALARM_SETTING", "VIBRATE");
                editor.commit();
            }
        });


        // [알림 설정] 무음 버튼 클릭 리스너
        btn_mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), uri);
                ringtone.stop();
                editor.putString("ALARM_SETTING", "MUTE");
                editor.commit();
            }
        });
        load();

        // 캘린더 색상 변경 버튼 클릭 리스너
        View.OnClickListener setColorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // 첫 번째 줄 컬러
                    case R.id.btn_set_babyPink:
                        break;
                    case R.id.btn_set_lightRed:
                        break;
                    case R.id.btn_set_red:
                        break;
                    case R.id.btn_set_lightYellow:
                        break;
                    case R.id.btn_set_yellow:
                        break;
                    case R.id.btn_set_orange:
                        break;
                    //두 번째 줄 컬러
                    case R.id.btn_set_lightGreen:
                        break;
                    case R.id.btn_set_green:
                        break;
                    case R.id.btn_set_darkGreen:
                        break;
                    case R.id.btn_set_sprayBlue:
                        break;
                    case R.id.btn_set_skyBlue:
                        break;
                    case R.id.btn_set_lightBlue:
                        break;
                    // 세 번째 줄 컬러
                    case R.id.btn_set_light_royal_blue:
                        break;
                    case R.id.btn_set_royalBlue:
                        break;
                    case R.id.btn_set_blue:
                        break;
                    case R.id.btn_set_pink:
                        break;
                    case R.id.btn_set_purple:
                        break;
                    case R.id.btn_set_darkPurple:
                        break;

                }
            }
        };

        // [캘린더 색상 설정] 첫 번째 줄
        btn_set_babyPink.setOnClickListener(setColorListener);
        btn_set_lightRed.setOnClickListener(setColorListener);
        btn_set_red.setOnClickListener(setColorListener);
        btn_set_lightYellow.setOnClickListener(setColorListener);
        btn_set_yellow.setOnClickListener(setColorListener);
        btn_set_orange.setOnClickListener(setColorListener);
        // [캘린더 색상 설정] 두 번째 줄
        btn_set_lightGreen.setOnClickListener(setColorListener);
        btn_set_green.setOnClickListener(setColorListener);
        btn_set_darkGreen.setOnClickListener(setColorListener);
        btn_set_sprayBlue.setOnClickListener(setColorListener);
        btn_set_skyBlue.setOnClickListener(setColorListener);
        btn_set_lightBlue.setOnClickListener(setColorListener);
        // [캘린더 색상 설정] 세 번째 줄
        btn_set_light_royal_blue.setOnClickListener(setColorListener);
        btn_set_royalBlue.setOnClickListener(setColorListener);
        btn_set_blue.setOnClickListener(setColorListener);
        btn_set_pink.setOnClickListener(setColorListener);
        btn_set_purple.setOnClickListener(setColorListener);
        btn_set_darkPurple.setOnClickListener(setColorListener);

    }

}

