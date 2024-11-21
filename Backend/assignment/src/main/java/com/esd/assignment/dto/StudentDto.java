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
public class StudentDto {

    private String studentName;
    private String studentEmail;
    private List<CourseDto> courseList;
}