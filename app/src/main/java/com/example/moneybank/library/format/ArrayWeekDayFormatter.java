package com.example.moneybank.library.format;

import java.time.DayOfWeek;

public class ArrayWeekDayFormatter implements WeekDayFormatter{
    private final CharSequence[] weekDayLabels;

    public ArrayWeekDayFormatter(final CharSequence[] weekDayLabels){
        if(weekDayLabels == null){
            throw new IllegalArgumentException("Cannot be null");
        }

        if(weekDayLabels.length != 7){
            throw new IllegalArgumentException("Array must contain exactly 7 elements");
        }
        this.weekDayLabels = weekDayLabels;
    }

    public CharSequence format(final DayOfWeek dayOfWeek){
        return weekDayLabels[dayOfWeek.getValue()-1];
    }
}
