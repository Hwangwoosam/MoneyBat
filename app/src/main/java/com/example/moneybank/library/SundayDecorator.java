package com.example.moneybank.library;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import java.time.DayOfWeek;
import java.util.Calendar;

public class SundayDecorator implements DayViewDecorator{

    public SundayDecorator(){
    }
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int sunday = day.getDate().with(DayOfWeek.SUNDAY).getDayOfMonth();
        return sunday == day.getDay();
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.RED));
    }
}
