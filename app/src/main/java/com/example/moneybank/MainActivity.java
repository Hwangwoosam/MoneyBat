package com.example.moneybank;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
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
import com.example.moneybank.library.OneDayDecorator;
import com.example.moneybank.library.SaturdayDecorator;
import com.example.moneybank.library.SundayDecorator;
import com.example.moneybank.library.format.format.ArrayWeekDayFormatter;
import com.example.moneybank.library.format.format.MonthArrayTitleFormatter;
import com.example.moneybank.library.format.format.TitleFormatter;

import java.io.File;
import java.time.LocalDate;


public class MainActivity extends AppCompatActivity {

    private MaterialCalendarView calendarView;
    private static final String TAG = "MyActivity";

    public ScrollView scroll_view;
    public TextView paytotal_view ,earntotal_view,sum_total_view , text_view1, monthName_view;
    public Button statistic_Btn,asset_Btn,home_Btn, log_Btn, add_Btn;

    public SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = init_database();
        initComponent();
        initSetting();
        init_tables();

        database.close();
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

    public void initSetting(){
        calendarView.setTitleFormatter(new MonthArrayTitleFormatter(getResources().getTextArray(R.array.custom_months)));
        calendarView.setWeekDayFormatter(new ArrayWeekDayFormatter(getResources().getTextArray(R.array.custom_weekdays)));

        calendarView.addDecorators(
                new DayDecorator(this),
                new SaturdayDecorator(),
                new SundayDecorator(),
                new OneDayDecorator(this)
        );

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

    private SQLiteDatabase init_database(){
        SQLiteDatabase db = null;

        File file = new File(getFilesDir(),"MoneyBank.db");

        System.out.println("PATH : " + file.toString());

        try{
            db=SQLiteDatabase.openOrCreateDatabase(file,null);
        }catch(SQLiteException e){
            e.printStackTrace();
        }

        if(db==null){
            System.out.println(("DB creation failed. " + file.getAbsolutePath()));
        }

        return db;
    }

    private void init_tables(){

        if (database != null){

            String CreateAccountTbl = "CREATE TABLE IF NOT EXISTS ACCOUNT_T (" +
                    "ID "   + "INTEGER " + "PRIMARY KEY " + "AUTOINCREMENT," +
                    "NAME " + "VARCHAR(255) NOT NULL," +
                    "TOTAL_SUM " + "INTEGER DEFAULT 0" + ")" ;

            database.execSQL(CreateAccountTbl);

            String CreatePayContent = "CREATE TABLE IF NOT EXISTS PAYCONTENT_T (" +
                    "ID " + "INTEGER " + "PRIMARY KEY " + "AUTOINCREMENT," +
                    "PayDATE " + "VARCHAR(255) NOT NULL," +
                    "MONEYDIREC " + "INTEGER NOT NULL," +
                    "PRICE " + "INTEGER DEFAULT 0," +
                    "USAGE " + "VARCHAR(255) NOT NULL" + ")" ;

            database.execSQL(CreatePayContent);

            String CreatePayInfo = "CREATE TABLE IF NOT EXISTS PAYINFO (" +
                    "ID " + "INTEGER " + "PRIMARY KEY " + "AUTOINCREMENT," +
                    "ACCOUNTID " + "INTEGER NOT NULL," +
                    "PAYCONTENTID " + "INTEGER NOT NULL," +
                    "FOREIGN KEY (ACCOUNTID) " + "REFERENCES ACCOUNT_T(ID)," +
                    "FOREIGN KEY (PAYCONTENTID) " + "REFERENCES PAYCONTENTID(ID))";

            database.execSQL(CreatePayInfo);

        }
    }

}