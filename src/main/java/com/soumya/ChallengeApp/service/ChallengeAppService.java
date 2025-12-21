package com.soumya.ChallengeApp.service;

import com.soumya.ChallengeApp.entity.ChallengeEntity;
import com.soumya.ChallengeApp.repository.ChallengeAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ChallengeAppService {

    @Autowired
    private ChallengeAppRepository challengeAppRepository;

    public ChallengeEntity saveEntry(ChallengeEntity myEntry) {
        challengeAppRepository.save(myEntry);
        return myEntry;
    }

    public List<ChallengeEntity> getAllChallenges() {
        return challengeAppRepository.findAll();
    }

    public void deleteById(String id) {
        challengeAppRepository.deleteById(id);
    }

    public Optional<ChallengeEntity> findById(String id) {
        return challengeAppRepository.findById(id);
    }


}
