package com.me.web.repositories;

import com.me.web.models.Reading;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReadingRepository extends MongoRepository<Reading, String> {
}
