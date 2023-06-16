package com.example.moneybank.library;

public interface DateRangeIndex {
    int getCount();
    int indexOf(CalendarDay day);

    CalendarDay getItem(int position);
}
