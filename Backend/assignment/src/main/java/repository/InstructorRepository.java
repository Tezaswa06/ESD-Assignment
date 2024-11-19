package repository;

import entities.Instructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface InstructorRepository extends MongoRepository<Instructor, String> {
}
