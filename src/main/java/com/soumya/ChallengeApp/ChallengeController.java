package com.soumya.ChallengeApp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/challenges")
public class ChallengeController {
    private ChallengeService challengeService;
    public ChallengeController(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @GetMapping
    public List<Challenge> getAllChallenges() {
        return challengeService.getAllChallenges();
    }

    @PostMapping
    public String addChallenge(@RequestBody Challenge challenge) {
        boolean isChallengeAdded = challengeService.addChallenge(challenge);
        if (isChallengeAdded) {
            return "Challenge added successfully";
        } else {
            return "Adding a challenge failed";
        }
    }

    @GetMapping("/{month}")
    public ResponseEntity<Object> getChallenge(@PathVariable String month) {
        List<Challenge> matchingChallenges = challengeService.getChallenge(month);
        if (!matchingChallenges.isEmpty()) {
            return new ResponseEntity<>(matchingChallenges, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallenge(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        boolean isChallengeUpdated = challengeService.updateChallenge(id, updatedChallenge);
        if (isChallengeUpdated) {
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Updating a challenge failed", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        boolean isChallengeDeleted = challengeService.deleteChallenge(id);
        if (isChallengeDeleted) {
            return new ResponseEntity<>("Challenge deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Deleting a challenge failed", HttpStatus.NOT_FOUND);
        }
    }
}
