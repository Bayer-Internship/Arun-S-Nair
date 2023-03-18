package com.tmdb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmdb.model.Student;
@Repository
public interface StudentRepository extends MongoRepository<Student, Integer>{
	
}
