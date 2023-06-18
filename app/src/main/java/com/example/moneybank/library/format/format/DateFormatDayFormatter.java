package com.example.moneybank.library.format.format;

import androidx.annotation.NonNull;

import com.example.moneybank.library.CalendarDay;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateFormatDayFormatter implements DayFormatter{

    private final DateTimeFormatter dateFormat;

    public DateFormatDayFormatter(){
        this(DateTimeFormatter.ofPattern(DEFAULT_FORMAT, Locale.getDefault()));
    }

    public DateFormatDayFormatter(@NonNull final DateTimeFormatter format){
        this.dateFormat = format;
    }

    @NonNull public String format(@NonNull final CalendarDay day){
        return dateFormat.format(day.getDate());
    }
}
