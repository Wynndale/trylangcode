package com.kplo.scheduler;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.kplo.scheduler.decorators.EventDecorator;
import com.kplo.scheduler.decorators.OneDayDecorator;
import com.kplo.scheduler.decorators.SaturdayDecorator;
import com.kplo.scheduler.decorators.SundayDecorator;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.format.DateFormatDayFormatter;
import com.prolificinteractive.materialcalendarview.format.DayFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;

public class CalendarActivity extends AppCompatActivity {

    String time,kcal,menu;
    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();
    Cursor cursor;
    MaterialCalendarView materialCalendarView;
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    TextView toolbar_text;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        setToolbar();


        //오늘 날짜 불러와서 툴바 텍스트 수정
        CalendarDay today = CalendarDay.today();
        String today_year = Integer.toString(today.getYear());
        String today_month = Integer.toString(today.getMonth());

        toolbar_text = (TextView)findViewById(R.id.toolbar_text);

        String text = today_year + "년 " + today_month + "월";
        toolbar_text.setText(text);


        materialCalendarView = (MaterialCalendarView)findViewById(R.id.calendarView);

        //캘린더 위의 톱바 비활성화
        materialCalendarView.setTopbarVisible(false);
        //현재 날짜를 선택
        materialCalendarView.setSelectedDate(today);

        //state().edit()를 통해 뭘 어떻게 보여줄 것이다를 commit() 하기 위한 코드
        materialCalendarView.state().edit()
                .setFirstDayOfWeek(Calendar.SUNDAY) //한 주의 시작은 일요일로 지정
                .setMinimumDate(CalendarDay.from(2017, 0, 1)) // 달력의 시작
                .setMaximumDate(CalendarDay.from(2030, 11, 31)) // 달력의 끝
                .setCalendarDisplayMode(CalendarMode.MONTHS) //월로 달력을 표시되게 구성
                .commit(); //위의 코드가 작성되게 commit

        //달력을 4주, 5주, 6주 등등 주차에 맞게 높이를 dynamic하게 보여줄 것이다는 설정
        materialCalendarView.setDynamicHeightEnabled(true);

        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                oneDayDecorator);

        String[] result = {"2017,03,18","2017,04,18","2017,05,18","2017,06,18"};

        new ApiSimulator(result).executeOnExecutor(Executors.newSingleThreadExecutor());


        // 날짜 클릭 시 이벤트
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int Year = date.getYear();
                int Month = date.getMonth() + 1;
                int Day = date.getDay();

                Log.i("Year test", Year + "");
                Log.i("Month test", Month + "");
                Log.i("Day test", Day + "");

                String shot_Day = Year + "," + Month + "," + Day;

                Log.i("shot_Day test", shot_Day + "");
                materialCalendarView.clearSelection();


                materialCalendarView.addDecorator(new EventDecorator(Color.RED, date ,CalendarActivity.this));

                Toast.makeText(getApplicationContext(), shot_Day , Toast.LENGTH_SHORT).show();
            }
        });

        //달력이 변화할 때 이벤트
        materialCalendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
                String Year = Integer.toString(date.getYear());
                String Month = Integer.toString(date.getMonth() + 1);

                CalendarDay today = CalendarDay.today();
                String today_year = Integer.toString(today.getYear());
                String today_month = Integer.toString(today.getMonth());

                String text = today_year + "년 " + today_month + "월";
                toolbar_text.setText(text);

                // 선택된 연도와 월이 오늘 날짜의 연도와 월과 다르면 선택된 날짜의 연도와 월로 바꾸기
                if(!Year.equals(today_year) || !Month.equals(today_month)){
                    text = Year + "년 " + Month + "월";
                }

                toolbar_text.setText(text);


                Toast.makeText(getApplicationContext(), text , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private class ApiSimulator extends AsyncTask<Void, Void, List<CalendarDay>> {

        String[] Time_Result;

        ApiSimulator(String[] Time_Result){
            this.Time_Result = Time_Result;
        }

        @Override
        protected List<CalendarDay> doInBackground(@NonNull Void... voids) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Calendar calendar = Calendar.getInstance();
            ArrayList<CalendarDay> dates = new ArrayList<>();

            /*특정날짜 달력에 점표시해주는곳*/
            /*월은 0이 1월 년,일은 그대로*/
            //string 문자열인 Time_Result 을 받아와서 ,를 기준으로짜르고 string을 int 로 변환
            for (int i = 0; i < Time_Result.length; i++) {


                //이부분에서 day를 선언하면 초기 값에 오늘 날짜 데이터 들어간다.
                //오늘 날짜 데이터를 첫 번째 인자로 넣기 때문에 데이터가 하나씩 밀려 마지막 데이터는 표시되지 않고, 오늘 날짜 데이터가 표시 됨.
                // day선언 주석처리

                //                CalendarDay day = CalendarDay.from(calendar);
                //                Log.e("데이터 확인","day"+day);
                String[] time = Time_Result[i].split(",");

                int year = Integer.parseInt(time[0]);
                int month = Integer.parseInt(time[1]);
                int dayy = Integer.parseInt(time[2]);

                //선언문을 아래와 같은 위치에 선언
                //먼저 .set 으로 데이터를 설정한 다음 CalendarDay day = CalendarDay.from(calendar); 선언해주면 첫 번째 인자로 새로 정렬한 데이터를 넣어 줌.
                calendar.set(year, month - 1, dayy);
                CalendarDay day = CalendarDay.from(calendar);
                dates.add(day);

            }



            return dates;
        }

        @Override
        protected void onPostExecute(@NonNull List<CalendarDay> calendarDays) {
            super.onPostExecute(calendarDays);

            if (isFinishing()) {
                return;
            }

//            //일정 dots 색상 배열
//            String[] colors = getResources().getStringArray(R.array.colors);
//
//            materialCalendarView.addDecorator(new EventDecorator(colors, calendarDays,CalendarActivity.this));
        }
    }


    /* 툴바 및 툴바기능 설정 함수.
     * onCreate에서 호출
     * 클래스 내 DrawerLayout drawerLayout; NavigationView navView; Toolbar toolbar; 선언 필요
     */
    protected void setToolbar(){
        //툴바 설정
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);    //기존 title 지우기
        actionBar.setDisplayHomeAsUpEnabled(true);      //뒤로가기 버튼 생성. 이 버튼을 메뉴바 버튼으로 사용
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu); //뒤로가기 버튼 아이콘 -> 메뉴 아이콘 변경


        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                int id = menuItem.getItemId();
                String title = menuItem.getTitle().toString();

                if(id == R.id.dating){
                    Toast.makeText(context, title + ": dating calendar", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.work){
                    Toast.makeText(context, title + ": work calendar", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.daily){
                    Toast.makeText(context, title + ": daily calendar", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.event){
//                    Intent intent = new Intent(getApplicationContext(), Graph.class);
//                    startActivity(intent);
                    Toast.makeText(context, title + ": event calendar", Toast.LENGTH_SHORT).show();
                }
                else if(id == R.id.logout){
//                    Intent intent = new Intent(getApplicationContext(), Graph.class);
//                    startActivity(intent);
                    Toast.makeText(context, title + ": log out", Toast.LENGTH_SHORT).show();
                }

                //menuItem.setChecked(false);
                return true;
            }
        });
    }
    //툴바 우측에 버튼 생성 (설정버튼)
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_option_btn, menu);
        return true;
    }

    //툴바에 버튼 클릭 시
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            //네비게이션드로어
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            //설정버튼
            case R.id.setting:
                Intent intent = new Intent(getApplicationContext(), Setting.class);
                startActivity(intent);
                Toast.makeText(context, "setting page", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    //네비게이션 열려있을때 뒤로가기로 버튼 닫기
    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
}