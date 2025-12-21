package com.soumya.ChallengeApp.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * ChallengeEntity - Represents a challenge entry in MongoDB
 * Uses Lombok @Data annotation to auto-generate getters, setters, and toString
 */
@Document(collection = "challenge_entries")
@Data
public class ChallengeEntity {
    @Id
    private String id;
    private String month;
    private String description;
    private LocalDateTime date;

    /**
     * No-arg constructor for MongoDB
     * Automatically generates a UUID for the id field
     */
    public ChallengeEntity() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Constructor with all fields
     *
     * @param month the month of the challenge
     * @param description the description of the challenge
     * @param date the date of the challenge
     */
    public ChallengeEntity(String month, String description, LocalDateTime date) {
        this.id = UUID.randomUUID().toString();
        this.month = month;
        this.description = description;
        this.date = date;
    }
}


