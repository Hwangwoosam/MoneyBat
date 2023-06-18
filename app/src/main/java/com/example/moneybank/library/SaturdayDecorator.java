package com.example.moneybank.library;

import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import java.time.DayOfWeek;

public class SaturdayDecorator implements DayViewDecorator{

    public  SaturdayDecorator(){
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        int saturday = day.getDate().with(DayOfWeek.SATURDAY).getDayOfMonth();
        return saturday == day.getDay();
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new ForegroundColorSpan(Color.BLUE));
    }
}
