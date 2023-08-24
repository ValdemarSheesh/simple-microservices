package com.example.middleservice.repo;

import com.example.middleservice.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepo extends MongoRepository<Book, String> {
}
