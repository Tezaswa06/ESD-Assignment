package com.esd.assignment.dto;

import com.esd.assignment.entities.Course;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InstructorDto {

    private String instructorName;
    private List<Course> courseList;
    private String instructorEmail;
}
