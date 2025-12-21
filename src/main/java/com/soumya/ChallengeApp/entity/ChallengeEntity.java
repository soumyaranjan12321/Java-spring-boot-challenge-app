package com.soumya.ChallengeApp.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "challenge_entries")
public class ChallengeEntity {
    @Id
    private String id;
    private String month;
    private String description;
    private LocalDateTime date;

    // No-arg constructor for MongoDB
    public ChallengeEntity() {
        this.id = UUID.randomUUID().toString();
    }

    // Constructor with all fields
    public ChallengeEntity(String month, String description, LocalDateTime date) {
        this.id = UUID.randomUUID().toString();
        this.month = month;
        this.description = description;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ChallengeEntity{" +
                "id='" + id + '\'' +
                ", month='" + month + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}

