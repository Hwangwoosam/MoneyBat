package com.example.moneybank.library.format.format;

import java.time.DayOfWeek;

public interface WeekDayFormatter {

    CharSequence format(DayOfWeek dayOfWeek);

    WeekDayFormatter DEFAULT = new CalendarWeekDayFormatter();
}
