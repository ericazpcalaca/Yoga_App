package com.example.yoga_app;

public class ProgressUser {

    private int totalWorkout;
    private int totalMinutes;
    private int lastFinishedMonth;
    private int lastFinishedDay;
    private int lastFinishedYear;
    private int bestStreak;
    private int currentStreak;

    public ProgressUser() {
    }

    public ProgressUser(int totalWorkout, int totalMinutes, int lastFinishedMonth, int lastFinishedDay, int lastFinishedYear, int bestStreak, int currentStreak) {
        this.totalWorkout = totalWorkout;
        this.totalMinutes = totalMinutes;
        this.lastFinishedMonth = lastFinishedMonth;
        this.lastFinishedDay = lastFinishedDay;
        this.lastFinishedYear = lastFinishedYear;
        this.bestStreak = bestStreak;
        this.currentStreak = currentStreak;
    }

    public int getLastFinishedMonth() {
        return lastFinishedMonth;
    }

    public void setLastFinishedMonth(int lastFinishedMonth) {
        this.lastFinishedMonth = lastFinishedMonth;
    }

    public int getLastFinishedDay() {
        return lastFinishedDay;
    }

    public void setLastFinishedDay(int lastFinishedDay) {
        this.lastFinishedDay = lastFinishedDay;
    }

    public int getLastFinishedYear() {
        return lastFinishedYear;
    }

    public void setLastFinishedYear(int lastFinishedYear) {
        this.lastFinishedYear = lastFinishedYear;
    }

    public int getTotalWorkout() {
        return totalWorkout;
    }

    public void setTotalWorkout(int totalWorkout) {
        this.totalWorkout = totalWorkout;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public int getBestStreak() {
        return bestStreak;
    }

    public void setBestStreak(int bestStreak) {
        this.bestStreak = bestStreak;
    }

    public int getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(int currentStreak) {
        this.currentStreak = currentStreak;
    }

}
