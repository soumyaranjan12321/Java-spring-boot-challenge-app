package com.soumya.ChallengeApp;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChallengeService {
    private List<Challenge> challenges = new ArrayList<>();
    private Long nextId = 1L;

    public ChallengeService() {
        //Challenge c1 = new Challenge(1L, "January", "Learn Java");
        //challenges.add(c1);
    }

    public List<Challenge> getAllChallenges() {
        return challenges;
    }

    public boolean addChallenge(Challenge challenge) {
        if (challenge != null) {
            challenge.setId(nextId++);
            challenges.add(challenge);
            return true;
        } else {
            return false;
        }
    }

    public List<Challenge> getChallenge(String month) {
        List<Challenge> matchingChallenges = new ArrayList<>();
        for (Challenge challenge : challenges) {
            if (challenge.getMonth().equalsIgnoreCase(month)) {
                matchingChallenges.add(challenge);
            }
        }

        return matchingChallenges;
    }

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

    public boolean deleteChallenge(Long id) {
        for (Challenge challenge : challenges) {
            if (challenge.getId().equals(id)) {
                challenges.remove(challenge);
                return true;
            }
        }
        return false;
        //return challenges.removeIf(challenge -> challenge.getId().equals(id));
    }
}
