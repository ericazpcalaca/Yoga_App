package com.example.yoga_app;

public class ProgressUser {

    private int totalWorkout;
    private int totalMinutes;
    private int lastFinishedWorkout;
    private int bestStreak;


    private int currentStreak;

    public ProgressUser() {
    }

    public ProgressUser(int totalWorkout, int totalMinutes, int lastFinishedWorkout, int bestStreak, int currentStreak) {
        this.totalWorkout = totalWorkout;
        this.totalMinutes = totalMinutes;
        this.lastFinishedWorkout = lastFinishedWorkout;
        this.bestStreak = bestStreak;
        this.currentStreak = currentStreak;
    }

    public ProgressUser(int totalWorkout, int totalMinutes, int lastFinishedWorkout, int bestStreak) {
        this.totalWorkout = totalWorkout;
        this.totalMinutes = totalMinutes;
        this.lastFinishedWorkout = lastFinishedWorkout;
        this.bestStreak = bestStreak;
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

    public int getLastFinishedWorkout() {
        return lastFinishedWorkout;
    }

    public void setLastFinishedWorkout(int lastFinishedWorkout) {
        this.lastFinishedWorkout = lastFinishedWorkout;
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
