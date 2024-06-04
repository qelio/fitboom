package com.fitexpert.fitboom;

public class DailyTrenning {
    int id, user_id, trenning_type, date_day, date_month, date_year;
    int exercise_1, exercise_2, exercise_3, exercise_4, exercise_5, exercise_6;
    int exercise_id_1, exercise_id_2, exercise_id_3, exercise_id_4, exercise_id_5, exercise_id_6;
    int exercise_1_param, exercise_2_param, exercise_3_param, exercise_4_param, exercise_5_param, exercise_6_param;
    String exercise_1_type, exercise_2_type, exercise_3_type, exercise_4_type, exercise_5_type, exercise_6_type;
    String trenning_date;

    public DailyTrenning() {

    }

    public DailyTrenning(int id, int user_id, int trenning_type, int date_day, int date_month, int date_year, int exercise_1, int exercise_2, int exercise_3, int exercise_4, int exercise_5, int exercise_6, int exercise_id_1, int exercise_id_2, int exercise_id_3, int exercise_id_4, int exercise_id_5, int exercise_id_6, int exercise_1_param, int exercise_2_param, int exercise_3_param, int exercise_4_param, int exercise_5_param, int exercise_6_param, String exercise_1_type, String exercise_2_type, String exercise_3_type, String exercise_4_type, String exercise_5_type, String exercise_6_type, String trenning_date) {
        this.id = id;
        this.user_id = user_id;
        this.trenning_type = trenning_type;
        this.date_day = date_day;
        this.date_month = date_month;
        this.date_year = date_year;
        this.exercise_1 = exercise_1;
        this.exercise_2 = exercise_2;
        this.exercise_3 = exercise_3;
        this.exercise_4 = exercise_4;
        this.exercise_5 = exercise_5;
        this.exercise_6 = exercise_6;
        this.exercise_id_1 = exercise_id_1;
        this.exercise_id_2 = exercise_id_2;
        this.exercise_id_3 = exercise_id_3;
        this.exercise_id_4 = exercise_id_4;
        this.exercise_id_5 = exercise_id_5;
        this.exercise_id_6 = exercise_id_6;
        this.exercise_1_param = exercise_1_param;
        this.exercise_2_param = exercise_2_param;
        this.exercise_3_param = exercise_3_param;
        this.exercise_4_param = exercise_4_param;
        this.exercise_5_param = exercise_5_param;
        this.exercise_6_param = exercise_6_param;
        this.exercise_1_type = exercise_1_type;
        this.exercise_2_type = exercise_2_type;
        this.exercise_3_type = exercise_3_type;
        this.exercise_4_type = exercise_4_type;
        this.exercise_5_type = exercise_5_type;
        this.exercise_6_type = exercise_6_type;
        this.trenning_date = trenning_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTrenning_type() {
        return trenning_type;
    }

    public void setTrenning_type(int trenning_type) {
        this.trenning_type = trenning_type;
    }

    public int getDate_day() {
        return date_day;
    }

    public void setDate_day(int date_day) {
        this.date_day = date_day;
    }

    public int getDate_month() {
        return date_month;
    }

    public void setDate_month(int date_month) {
        this.date_month = date_month;
    }

    public int getDate_year() {
        return date_year;
    }

    public void setDate_year(int date_year) {
        this.date_year = date_year;
    }

    public int getExercise_1() {
        return exercise_1;
    }

    public void setExercise_1(int exercise_1) {
        this.exercise_1 = exercise_1;
    }

    public int getExercise_2() {
        return exercise_2;
    }

    public void setExercise_2(int exercise_2) {
        this.exercise_2 = exercise_2;
    }

    public int getExercise_3() {
        return exercise_3;
    }

    public void setExercise_3(int exercise_3) {
        this.exercise_3 = exercise_3;
    }

    public int getExercise_4() {
        return exercise_4;
    }

    public void setExercise_4(int exercise_4) {
        this.exercise_4 = exercise_4;
    }

    public int getExercise_5() {
        return exercise_5;
    }

    public void setExercise_5(int exercise_5) {
        this.exercise_5 = exercise_5;
    }

    public int getExercise_6() {
        return exercise_6;
    }

    public void setExercise_6(int exercise_6) {
        this.exercise_6 = exercise_6;
    }

    public int getExercise_id_1() {
        return exercise_id_1;
    }

    public void setExercise_id_1(int exercise_id_1) {
        this.exercise_id_1 = exercise_id_1;
    }

    public int getExercise_id_2() {
        return exercise_id_2;
    }

    public void setExercise_id_2(int exercise_id_2) {
        this.exercise_id_2 = exercise_id_2;
    }

    public int getExercise_id_3() {
        return exercise_id_3;
    }

    public void setExercise_id_3(int exercise_id_3) {
        this.exercise_id_3 = exercise_id_3;
    }

    public int getExercise_id_4() {
        return exercise_id_4;
    }

    public void setExercise_id_4(int exercise_id_4) {
        this.exercise_id_4 = exercise_id_4;
    }

    public int getExercise_id_5() {
        return exercise_id_5;
    }

    public void setExercise_id_5(int exercise_id_5) {
        this.exercise_id_5 = exercise_id_5;
    }

    public int getExercise_id_6() {
        return exercise_id_6;
    }

    public void setExercise_id_6(int exercise_id_6) {
        this.exercise_id_6 = exercise_id_6;
    }

    public int getExercise_1_param() {
        return exercise_1_param;
    }

    public void setExercise_1_param(int exercise_1_param) {
        this.exercise_1_param = exercise_1_param;
    }

    public int getExercise_2_param() {
        return exercise_2_param;
    }

    public void setExercise_2_param(int exercise_2_param) {
        this.exercise_2_param = exercise_2_param;
    }

    public int getExercise_3_param() {
        return exercise_3_param;
    }

    public void setExercise_3_param(int exercise_3_param) {
        this.exercise_3_param = exercise_3_param;
    }

    public int getExercise_4_param() {
        return exercise_4_param;
    }

    public void setExercise_4_param(int exercise_4_param) {
        this.exercise_4_param = exercise_4_param;
    }

    public int getExercise_5_param() {
        return exercise_5_param;
    }

    public void setExercise_5_param(int exercise_5_param) {
        this.exercise_5_param = exercise_5_param;
    }

    public int getExercise_6_param() {
        return exercise_6_param;
    }

    public void setExercise_6_param(int exercise_6_param) {
        this.exercise_6_param = exercise_6_param;
    }

    public String getExercise_1_type() {
        return exercise_1_type;
    }

    public void setExercise_1_type(String exercise_1_type) {
        this.exercise_1_type = exercise_1_type;
    }

    public String getExercise_2_type() {
        return exercise_2_type;
    }

    public void setExercise_2_type(String exercise_2_type) {
        this.exercise_2_type = exercise_2_type;
    }

    public String getExercise_3_type() {
        return exercise_3_type;
    }

    public void setExercise_3_type(String exercise_3_type) {
        this.exercise_3_type = exercise_3_type;
    }

    public String getExercise_4_type() {
        return exercise_4_type;
    }

    public void setExercise_4_type(String exercise_4_type) {
        this.exercise_4_type = exercise_4_type;
    }

    public String getExercise_5_type() {
        return exercise_5_type;
    }

    public void setExercise_5_type(String exercise_5_type) {
        this.exercise_5_type = exercise_5_type;
    }

    public String getExercise_6_type() {
        return exercise_6_type;
    }

    public void setExercise_6_type(String exercise_6_type) {
        this.exercise_6_type = exercise_6_type;
    }

    public String getTrenning_date() {
        return trenning_date;
    }

    public void setTrenning_date(String trenning_date) {
        this.trenning_date = trenning_date;
    }

    public int getCountExercises() {
        return exercise_1 + exercise_2 + exercise_3 + exercise_4 + exercise_5 + exercise_6;
    }
}
