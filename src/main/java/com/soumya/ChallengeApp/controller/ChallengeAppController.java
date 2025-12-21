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

    @PostMapping("/save")
    public ChallengeEntity saveChallengeEntries(@RequestBody ChallengeEntity myEntry) {
        myEntry.setDate(LocalDateTime.now());
        challengeAppService.saveEntry(myEntry);
        return myEntry;
    }

    @GetMapping("/list")
    public List<ChallengeEntity> getAllChallenges() {
        return challengeAppService.getAllChallenges();
    }

    @GetMapping("/list/{id}")
    public ChallengeEntity getChallengeById(@PathVariable String id) {
        //return challengeAppService.findById(id).orElseThrow(() -> new RuntimeException("Entry not found"));
        return challengeAppService.findById(id).orElse(null);
    }

    @PutMapping("/update/{id}")
    public ChallengeEntity updateEntries(@PathVariable String id, @RequestBody ChallengeEntity newEntry) {
        ChallengeEntity old = challengeAppService.findById(id).orElseThrow(() -> new RuntimeException("Entry not found"));
        if (old != null) {
            old.setMonth(newEntry.getMonth()!= null && !newEntry.getMonth().isEmpty() ? newEntry.getMonth() : old.getMonth());
            old.setDescription(newEntry.getDescription() != null && !newEntry.getDescription().isEmpty() ? newEntry.getDescription() : old.getDescription());
            //old.setDate(LocalDateTime.now()); //Not required, want to keep created data
        }
        challengeAppService.saveEntry(old);
        return old;
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteById(@PathVariable String id) {
        challengeAppService.deleteById(id);
        return true;
    }

}
