package com.soumya.ChallengeApp.repository;

import com.soumya.ChallengeApp.entity.ChallengeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChallengeAppRepository extends MongoRepository<ChallengeEntity, String> {
}
