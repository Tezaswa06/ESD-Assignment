package entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Instructor {

    @Id
    private String instructorId;

    private String instructorName;

    private String instructorEmail;

    private String instructorPhone;

    @DBRef
    private List<Course> courses;
}
