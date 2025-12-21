package com.soumya.ChallengeApp.controller;

import com.soumya.ChallengeApp.entity.ChallengeEntity;
import com.soumya.ChallengeApp.service.ChallengeAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for managing Challenge entries
 */
@RestController
@RequestMapping("/challenge")
public class ChallengeAppController {

    @Autowired
    private ChallengeAppService challengeAppService;

    /**
     * Save a new challenge entry
     *
     * @param entry the challenge entry to save
     * @return ResponseEntity with saved entry and 201 Created status
     */
    @PostMapping("/save")
    public ResponseEntity<ChallengeEntity> saveChallengeEntry(@RequestBody ChallengeEntity entry) {
        entry.setDate(LocalDateTime.now());
        ChallengeEntity savedEntry = challengeAppService.saveEntry(entry);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEntry);
    }

    /**
     * Get all challenge entries
     *
     * @return ResponseEntity with list of all challenge entries
     */
    @GetMapping("/list")
    public ResponseEntity<List<ChallengeEntity>> getAllChallenges() {
        List<ChallengeEntity> challenges = challengeAppService.getAllChallenges();
        return ResponseEntity.ok(challenges);
    }

    /**
     * Get a challenge entry by ID
     *
     * @param id the challenge entry ID
     * @return ResponseEntity with the challenge entry or 404 if not found
     */
    @GetMapping("/list/{id}")
    public ResponseEntity<ChallengeEntity> getChallengeById(@PathVariable String id) {
        return challengeAppService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Update an existing challenge entry
     *
     * @param id the challenge entry ID to update
     * @param newEntry the updated challenge entry data
     * @return ResponseEntity with updated entry or 404 if not found
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ChallengeEntity> updateChallengeEntry(@PathVariable String id, @RequestBody ChallengeEntity newEntry) {
        return challengeAppService.findById(id)
                .map(existingEntry -> {
                    if (isNotEmpty(newEntry.getMonth())) {
                        existingEntry.setMonth(newEntry.getMonth());
                    }
                    if (isNotEmpty(newEntry.getDescription())) {
                        existingEntry.setDescription(newEntry.getDescription());
                    }
                    ChallengeEntity updatedEntry = challengeAppService.saveEntry(existingEntry);
                    return ResponseEntity.ok(updatedEntry);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Delete a challenge entry by ID
     *
     * @param id the challenge entry ID to delete
     * @return ResponseEntity with 204 No Content status
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        challengeAppService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Helper method to check if a string is not empty
     *
     * @param value the string value to check
     * @return true if the value is not null and not empty
     */
    private boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }

}


