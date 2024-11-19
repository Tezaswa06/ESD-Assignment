package com.esd.assignment.repository;

import com.esd.assignment.entities.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends MongoRepository<Instructor, String> {

    Optional<Instructor> findByInstructorName(String instructorName);
}
