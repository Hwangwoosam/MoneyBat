package com.example.moneybank.library.format;

import com.example.moneybank.library.CalendarDay;
public interface TitleFormatter {
    String DEFAULT_FORMAT = "LLLL yyyy";

    TitleFormatter DEFAULT = new DateFormatTitleFormatter();

    CharSequence format(CalendarDay day);
}
