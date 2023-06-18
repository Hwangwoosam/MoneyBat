package com.example.moneybank.library.format.format;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarWeekDayFormatter implements WeekDayFormatter{
    public CharSequence format(final DayOfWeek dayOfWeek){
        return dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault());
    }
}
