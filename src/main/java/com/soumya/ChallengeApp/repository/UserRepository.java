package com.soumya.ChallengeApp.repository;

import com.soumya.ChallengeApp.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repository for User entity
 * Provides CRUD operations and custom queries for User documents
 */
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}

