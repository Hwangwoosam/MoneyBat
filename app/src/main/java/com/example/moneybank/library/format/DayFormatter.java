package com.example.moneybank.library.format;

import androidx.annotation.NonNull;

import com.example.moneybank.library.CalendarDay;

public interface DayFormatter {
    String DEFAULT_FORMAT = "d";

    DayFormatter DEFAULT = new DateFormatDayFormatter();

    String format(@NonNull CalendarDay day);
}
