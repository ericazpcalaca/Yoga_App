package com.example.yoga_app;

public class User {

    private String userEmail;
    private int userAge;
    private double currentWeight;
    private double targetWeight;
    private int userHeight;
    private String userGender;

    public User(String userEmail, int userAge, double currentWeight, double targetWeight, int userHeight, String userGender) {
        this.userEmail = userEmail;
        this.userAge = userAge;
        this.currentWeight = currentWeight;
        this.targetWeight = targetWeight;
        this.userHeight = userHeight;
        this.userGender = userGender;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public double getCurrentWeight() {
        return currentWeight;
    }

    public void setCurrentWeight(double currentWeight) {
        this.currentWeight = currentWeight;
    }

    public double getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(double targetWeight) {
        this.targetWeight = targetWeight;
    }

    public int getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(int userHeight) {
        this.userHeight = userHeight;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
}
