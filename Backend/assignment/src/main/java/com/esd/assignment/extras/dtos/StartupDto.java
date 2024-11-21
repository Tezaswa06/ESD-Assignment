package com.esd.assignment.extras.dtos;

import com.esd.assignment.dto.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StartupDto {

    private List<AdminDto> admins;
    private List<CourseDto> courses;
    private List<StudentDto> students;
    private List<InstructorDto> instructors;
    private List<CourseAndInstructorMappingDto> courseAndInstructorMappings;
    private List<CourseAndStudentMappingDto> courseAndStudentMappings;
}

