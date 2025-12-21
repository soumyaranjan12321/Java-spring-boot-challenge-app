package com.soumya.ChallengeApp.service;

import com.soumya.ChallengeApp.entity.ChallengeEntity;
import com.soumya.ChallengeApp.repository.ChallengeAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChallengeAppService {

    @Autowired
    private ChallengeAppRepository challengeAppRepository;

    public void saveEntry(ChallengeEntity myEntry) {
        challengeAppRepository.save(myEntry);
    }

    public List<ChallengeEntity> getAllChallenges() {
        return challengeAppRepository.findAll();
    }


}
