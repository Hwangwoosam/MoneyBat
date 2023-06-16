package com.example.moneybank.library;

public interface OnMonthChangedListener {

    /**
     * Called upon change of the selected day
     *
     * @param widget the view associated with this listener
     * @param date the month picked, as the first day of the month
     */
    void onMonthChanged(MaterialCalendarView widget, CalendarDay date);
}