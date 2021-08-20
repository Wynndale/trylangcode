package com.kplo.scheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatePickerActivity extends Activity {

    private int mYear = 0, mMonth = 0, mDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        CalendarDay today = CalendarDay.today();
        mYear = today.getYear();
        mMonth = today.getMonth();
        mDay = today.getDay();

        DatePicker datePicker = findViewById(R.id.date_picker);
        datePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int yy, int mm, int dd) {
                mYear = yy;
                mMonth = mm;
                mDay = dd;
            }
        });
    }

    public void OnEnterClick(View v) {
        Intent intent = new Intent();
        intent.putExtra("mYear", mYear);
        intent.putExtra("mMonth", mMonth);
        intent.putExtra("mDay", mDay);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void OnCancelClick(View v) {
        finish();
    }

}
