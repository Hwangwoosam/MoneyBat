package com.example.moneybank.library;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import androidx.core.content.ContextCompat;

import com.example.moneybank.R;

import java.time.LocalDate;

public class OneDayDecorator implements DayViewDecorator{
    private CalendarDay date;
    private Drawable drawable;

    public OneDayDecorator(Context context){
        date = CalendarDay.today();
        drawable = context.getDrawable(R.drawable.selected_date_decorator_downscaled_ratio_adjusted);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new StyleSpan(Typeface.BOLD));
        view.addSpan(new RelativeSizeSpan(1.4f));
        view.addSpan(new ForegroundColorSpan(Color.rgb(255,202,0)));
        view.setBackgroundDrawable(drawable);
    }

    public void setDate(LocalDate date) {
        this.date = CalendarDay.from(date);
    }
}
