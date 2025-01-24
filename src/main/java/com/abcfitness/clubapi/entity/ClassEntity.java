package com.abcfitness.clubapi.entity;

import java.time.LocalDate;

public class ClassEntity {
    private String name; // Class name
    private LocalDate startDate; // Start date of the class schedule
    private LocalDate endDate; // End date of the class schedule
    private String startTime; // Time when the class starts
    private int duration; // Duration of the class in minutes
    private int capacity; // Maximum capacity for the class

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

