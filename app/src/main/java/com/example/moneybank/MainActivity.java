package com.example.moneybank;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;


import com.example.moneybank.library.CalendarDay;
import com.example.moneybank.library.DayViewDecorator;
import com.example.moneybank.library.DayViewFacade;
import com.example.moneybank.library.MaterialCalendarView;
import com.example.moneybank.library.format.ArrayWeekDayFormatter;
import com.example.moneybank.library.format.MonthArrayTitleFormatter;
import com.example.moneybank.library.format.TitleFormatter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private static final String TAG = "MyActivity";

    public ScrollView scroll_view;
    public TextView paytotal_view ,earntotal_view,sum_total_view , text_view1, monthName_view;
    public Button statistic_Btn,asset_Btn,home_Btn, log_Btn, add_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponent();

        calendarView.state()
                .edit()
                .setFirstDayOfWeek(DayOfWeek.MONDAY)
                .commit();

        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));

        calendarView.addDecorator(new DayDecorator(this));

        calendarView.setTitleFormatter(new TitleFormatter() {
            @Override
            public CharSequence format(CalendarDay day) {
                LocalDate inputText = day.getDate();
                Log.d(TAG,"day " + inputText);
                String[] calendarHeaderElements = inputText.toString().split("-");
                StringBuilder calendarHeaderBuilder = new StringBuilder();
                calendarHeaderBuilder.append(calendarHeaderElements[0])
                        .append(" ")
                        .append(calendarHeaderElements[1]);
                Log.d(TAG,"builder: " + calendarHeaderBuilder.toString());
                return calendarHeaderBuilder.toString();
            }
        });
    }

    public void initComponent(){
        calendarView = findViewById(R.id.calendarView);
        scroll_view = findViewById(R.id.scrollview);
        paytotal_view = findViewById(R.id.paytotal);
        earntotal_view = findViewById(R.id.earntotal);
        sum_total_view = findViewById(R.id.sumtotal);
        text_view1 = findViewById(R.id.logtitle);

        statistic_Btn = findViewById(R.id.statistic_btn);
        asset_Btn = findViewById(R.id.asset_btn);
        home_Btn = findViewById(R.id.home_btn);
        log_Btn = findViewById(R.id.log_btn);
        add_Btn = findViewById(R.id.addButton);

        calendarView.setVisibility(View.VISIBLE);
        scroll_view.setVisibility(View.VISIBLE);
        paytotal_view.setVisibility(View.VISIBLE);
        earntotal_view.setVisibility(View.VISIBLE);
        sum_total_view.setVisibility(View.VISIBLE);
        text_view1.setVisibility(View.VISIBLE);

        statistic_Btn.setVisibility(View.VISIBLE);
        asset_Btn.setVisibility(View.VISIBLE);
        home_Btn.setVisibility(View.VISIBLE);
        log_Btn.setVisibility(View.VISIBLE);
        add_Btn.setVisibility(View.VISIBLE);
    }

    private static class DayDecorator implements DayViewDecorator {
        private final Drawable drawable;

        public DayDecorator(Context context){
            drawable = ContextCompat.getDrawable(context,R.drawable.calendar_selector);
        }

        public boolean shouldDecorate(CalendarDay day){
            return true;
        }

        public void decorate(DayViewFacade view){
            view.setSelectionDrawable(drawable);
        }
    }
}