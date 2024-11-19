package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Course {

    @Id
    private String courseId;

    private String courseName;

    private String courseDescription;

    private String courseInstructor;

    @DBRef
    private Instructor instructor;

    @DBRef
    private List<Student> students;
}
