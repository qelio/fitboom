package com.fitexpert.fitboom;

import java.io.Serializable;

public class FitUser implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id, age;
    private double current_weight, desired_weight, growth, monthly_budget;
    private String first_name, last_name, email, password, gender, device_info, intolerance, favorite_foods, unloved_foods;

    public FitUser () {
        this.id = id;
        this.first_name = "Данные не переданы";
        this.last_name = "Данные не переданы";
        this.email = "Данные не переданы";
        this.password = "Данные не переданы";
        this.gender = "Данные не переданы";
        this.age = 0;
        this.current_weight = 0;
        this.desired_weight = 0;
        this.growth = 0;
        this.monthly_budget = 0;
        this.intolerance = "Продукты не выбраны";
        this.favorite_foods = "Продукты не выбраны";
        this.unloved_foods = "Продукты не выбраны";
        this.device_info = "Данные не переданы";
    }

    public FitUser (int id, String first_name, String last_name, String email, String password, String gender, int age, double current_weight, double desired_weight, double growth, double monthly_budget, String device_info, String intolerance, String favorite_foods, String unloved_foods) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.current_weight = current_weight;
        this.desired_weight = desired_weight;
        this.growth = growth;
        this.monthly_budget = monthly_budget;
        this.device_info = device_info;
        this.intolerance = intolerance;
        this.favorite_foods = favorite_foods;
        this.unloved_foods = unloved_foods;
    }

    public int getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getCurrent_weight() {
        return current_weight;
    }

    public double getDesired_weight() {
        return desired_weight;
    }

    public double getGrowth() {
        return growth;
    }

    public double getMonthly_budget() {
        return monthly_budget;
    }

    public String getDevice_info() {
        return device_info;
    }

    public String getIntolerance() {
        return intolerance;
    }

    public String getFavorite_foods() {
        return favorite_foods;
    }

    public String getUnloved_foods() {
        return unloved_foods;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCurrent_weight(double current_weight) {
        this.current_weight = current_weight;
    }

    public void setDesired_weight(double desired_weight) {
        this.desired_weight = desired_weight;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public void setMonthly_budget(double monthly_budget) {
        this.monthly_budget = monthly_budget;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public void setIntolerance(String intolerance) {
        this.intolerance = intolerance;
    }

    public void setFavorite_foods(String favorite_foods) {
        this.favorite_foods = favorite_foods;
    }

    public void setUnloved_foods(String unloved_foods) {
        this.unloved_foods = unloved_foods;
    }
}
