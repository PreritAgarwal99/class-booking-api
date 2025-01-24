package com.abcfitness.clubapi.entity;

import java.time.LocalDate;

public class BookingEntity {
    private String memberName; // Name of the member
    private String className; // Name of the class
    private LocalDate participationDate; // Date of participation

    // Getters and setters
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDate getParticipationDate() {
        return participationDate;
    }

    public void setParticipationDate(LocalDate participationDate) {
        this.participationDate = participationDate;
    }
}

