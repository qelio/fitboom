package com.fitexpert.fitboom;

import java.io.Serializable;

public class MonthlyFoodDB implements Serializable {
    private int id, date_day;
    private String breakfast_name, lunch_name, dinner_name;
    private String breakfast_recept, lunch_recept, dinner_recept;
    private int breakfast_kkal, lunch_kkal, dinner_kkal;

    public MonthlyFoodDB(int id, int date_day, String breakfast_name, String lunch_name, String dinner_name, String breakfast_recept, String lunch_recept, String dinner_recept, int breakfast_kkal, int lunch_kkal, int dinner_kkal) {
        this.id = id;
        this.date_day = date_day;
        this.breakfast_name = breakfast_name;
        this.lunch_name = lunch_name;
        this.dinner_name = dinner_name;
        this.breakfast_recept = breakfast_recept;
        this.lunch_recept = lunch_recept;
        this.dinner_recept = dinner_recept;
        this.breakfast_kkal = breakfast_kkal;
        this.lunch_kkal = lunch_kkal;
        this.dinner_kkal = dinner_kkal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDate_day() {
        return date_day;
    }

    public void setDate_day(int date_day) {
        this.date_day = date_day;
    }

    public String getBreakfast_name() {
        return breakfast_name;
    }

    public void setBreakfast_name(String breakfast_name) {
        this.breakfast_name = breakfast_name;
    }

    public String getLunch_name() {
        return lunch_name;
    }

    public void setLunch_name(String lunch_name) {
        this.lunch_name = lunch_name;
    }

    public String getDinner_name() {
        return dinner_name;
    }

    public void setDinner_name(String dinner_name) {
        this.dinner_name = dinner_name;
    }

    public String getBreakfast_recept() {
        return breakfast_recept;
    }

    public void setBreakfast_recept(String breakfast_recept) {
        this.breakfast_recept = breakfast_recept;
    }

    public String getLunch_recept() {
        return lunch_recept;
    }

    public void setLunch_recept(String lunch_recept) {
        this.lunch_recept = lunch_recept;
    }

    public String getDinner_recept() {
        return dinner_recept;
    }

    public void setDinner_recept(String dinner_recept) {
        this.dinner_recept = dinner_recept;
    }

    public int getBreakfast_kkal() {
        return breakfast_kkal;
    }

    public void setBreakfast_kkal(int breakfast_kkal) {
        this.breakfast_kkal = breakfast_kkal;
    }

    public int getLunch_kkal() {
        return lunch_kkal;
    }

    public void setLunch_kkal(int lunch_kkal) {
        this.lunch_kkal = lunch_kkal;
    }

    public int getDinner_kkal() {
        return dinner_kkal;
    }

    public void setDinner_kkal(int dinner_kkal) {
        this.dinner_kkal = dinner_kkal;
    }
}
