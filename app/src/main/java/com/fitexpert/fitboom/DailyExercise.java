package com.fitexpert.fitboom;

public class DailyExercise {
    String title, description, type;
    int param;

    public DailyExercise() {
    }

    public DailyExercise(String title, String description, String type, int param) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.param = param;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getParam() {
        return param;
    }

    public void setParam(int param) {
        this.param = param;
    }
}
