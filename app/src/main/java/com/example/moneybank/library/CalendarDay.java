package com.example.moneybank.library;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.time.LocalDate;

public class CalendarDay implements Parcelable {

    @NonNull private final LocalDate date;

    private CalendarDay(final int year,final int month,final int day){
        date = LocalDate.of(year, month, day);
    }

    private  CalendarDay(@NonNull final LocalDate date){
        this.date = date;
    }

    @NonNull public static CalendarDay today(){
        return from(LocalDate.now());
    }

    @NonNull public static CalendarDay from(int year, int month, int day){
        return new CalendarDay(year, month, day);
    }

    public static CalendarDay from(@Nullable LocalDate date){
        if(date == null){
            return null;
        }

        return new CalendarDay(date);
    }

    public int getYear(){
        return date.getYear();
    }

    public int getMonth(){
        return date.getMonthValue();
    }

    public int getDay(){
        return date.getDayOfMonth();
    }

    @NonNull public LocalDate getDate(){
        return date;
    }

    public boolean isInRange(@Nullable CalendarDay minDate, @Nullable CalendarDay maxDate){
        return !(minDate != null && minDate.isAfter(this)) &&
                !(maxDate != null && maxDate.isBefore(this));
    }

    public boolean isBefore(@NonNull final CalendarDay other){
        return date.isBefore(other.getDate());
    }

    public boolean isAfter(@NonNull final  CalendarDay other){
        return date.isAfter(other.getDate());
    }

    public boolean equals(Object o){
        return o instanceof CalendarDay && date.equals(((CalendarDay) o).getDate());
    }

    public int hashCode(){
        return hashCode(date.getYear(),date.getMonthValue(),date.getDayOfMonth());
    }

    private static int hashCode(int year,int month,int day){
        return (year*10000) + (month*100) + day;
    }

    public String toString(){
        return "CalendarDay{" + date.getYear() + "-" + date.getMonth() + "-" +date.getDayOfMonth() +"}";
    }

    public CalendarDay(Parcel in){
        this(in.readInt(),in.readInt(),in.readInt());
    }

    public int describeContents(){
        return 0;
    }

    public void writeToParcel(Parcel dest,int flags){
        dest.writeInt(date.getYear());
        dest.writeInt(date.getMonthValue());
        dest.writeInt(date.getDayOfMonth());
    }

    public static final Creator<CalendarDay> CREATOR = new Creator<CalendarDay>(){
        public CalendarDay createFromParcel(Parcel in) {
            return new CalendarDay(in);
        }

        public CalendarDay[] newArray(int size){
            return new CalendarDay[size];
        }
    };


}
