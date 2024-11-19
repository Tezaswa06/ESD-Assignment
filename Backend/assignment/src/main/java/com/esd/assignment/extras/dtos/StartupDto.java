package com.esd.assignment.extras.dtos;

import com.esd.assignment.dto.AdminDto;
import com.esd.assignment.dto.CourseDto;
import com.esd.assignment.dto.InstructorDto;
import com.esd.assignment.dto.StudentDto;
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
}
