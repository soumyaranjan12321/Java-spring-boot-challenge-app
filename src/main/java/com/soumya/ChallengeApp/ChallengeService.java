// java
package com.soumya.ChallengeApp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {
    private final List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    public ChallengeService() {
        //Challenge c1 = new Challenge(1L, "January", "Learn Java");
        //challenges.add(c1);
    }

    /**
     * Retrieves all challenges currently stored in the service.
     *
     * @return a list of all challenges
     */
    public List<Challenge> getAllChallenges() {
        return challenges;
    }

    /**
     * Adds a new challenge to the service with an auto-assigned ID.
     *
     * @param challenge the challenge to add
     * @return true if the challenge was added successfully, false if challenge is null
     */
    public boolean addChallenge(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextId++);
            challenges.add(challenge);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Retrieves challenges matching the specified month (case-insensitive).
     *
     * @param month the month to search for
     * @return a list of challenges matching the month, or an empty list if none found
     */
    public List<Challenge> getChallenge(String month) {
        List<Challenge> matchingChallenges = new ArrayList<>();
        for (Challenge challenge : challenges) {
            if (challenge.getMonth().equalsIgnoreCase(month)) {
                matchingChallenges.add(challenge);
            }
        }

        return matchingChallenges;
    }

    /**
     * Updates an existing challenge with the specified ID.
     *
     * @param id the ID of the challenge to update
     * @param updatedChallenge the challenge object containing updated month and description
     * @return true if the challenge was updated successfully, false if ID not found
     */
    public boolean updateChallenge(Long id, Challenge updatedChallenge) {
        for (Challenge challenge : challenges) {
            if (challenge.getId().equals(id)) {
                challenge.setMonth(updatedChallenge.getMonth());
                challenge.setDescription(updatedChallenge.getDescription());
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes a challenge with the specified ID.
     *
     * @param id the ID of the challenge to delete
     * @return true if the challenge was deleted successfully, false if ID not found
     */
    public boolean deleteChallenge(Long id) {
        return challenges.removeIf(challenge -> challenge.getId().equals(id));
    }
}
