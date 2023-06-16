package com.example.moneybank.library.format;

import com.example.moneybank.library.CalendarDay;

import java.time.format.DateTimeFormatter;

public class DateFormatTitleFormatter implements TitleFormatter{
    private final DateTimeFormatter dateFormat;

    public DateFormatTitleFormatter(){
        this(DateTimeFormatter.ofPattern(DEFAULT_FORMAT));
    }

    public DateFormatTitleFormatter(final DateTimeFormatter format){
        this.dateFormat = format;
    }

    @Override
    public CharSequence format(final CalendarDay day) {
        return dateFormat.format(day.getDate());
    }
}
