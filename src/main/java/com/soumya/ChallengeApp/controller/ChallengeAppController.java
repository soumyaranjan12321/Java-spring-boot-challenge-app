package com.soumya.ChallengeApp.controller;

import com.soumya.ChallengeApp.entity.ChallengeEntity;
import com.soumya.ChallengeApp.service.ChallengeAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/challenge")
public class ChallengeAppController {

    @Autowired
    private ChallengeAppService challengeAppService;

    @PostMapping("/saveEntries")
    public boolean saveChallengeEntries(@RequestBody ChallengeEntity myEntry) {
        myEntry.setDate(LocalDateTime.now());
        challengeAppService.saveEntry(myEntry);
        return true;
    }

    @GetMapping("/getAllChallenges")
    public List<ChallengeEntity> getAllChallenges() {
        return challengeAppService.getAllChallenges();
    }

}
