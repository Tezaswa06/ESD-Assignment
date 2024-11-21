package com.esd.assignment.extras;

import com.esd.assignment.dto.*;
import com.esd.assignment.entities.Admin;
import com.esd.assignment.entities.Course;
import com.esd.assignment.entities.Instructor;
import com.esd.assignment.entities.Student;
import com.esd.assignment.extras.dtos.StartupDto;
import org.springframework.stereotype.Service;
import com.esd.assignment.repository.AdminRepository;
import com.esd.assignment.repository.CourseRepository;
import com.esd.assignment.repository.InstructorRepository;
import com.esd.assignment.repository.StudentRepository;

import java.util.List;

@Service
public class AuxService {
    private final AdminRepository adminRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final InstructorRepository instructorRepository;

    public AuxService(AdminRepository adminRepository, CourseRepository courseRepository, StudentRepository studentRepository, InstructorRepository instructorRepository) {
        this.adminRepository = adminRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.instructorRepository = instructorRepository;
    }

    private void addCourses(List<CourseDto> courseDtoList) {
        courseDtoList.forEach(courseDto -> {
            Course course = new Course();
            course.setCourseName(courseDto.getCourseName());
            course.setCourseDescription(courseDto.getDescription());
            courseRepository.save(course);
        });
    }


    private void addStudents(List<StudentDto> studentDtoList){
        studentDtoList.forEach(studentDto -> {
            Student student = new Student();
            student.setStudentName(studentDto.getStudentName());
            student.setStudentEmail(studentDto.getStudentEmail());
            studentRepository.save(student);
        });
    }

    private void addInstructors(List<InstructorDto> instructorDtoList){
        instructorDtoList.forEach(instructorDto -> {
            Instructor instructor = new Instructor();
            instructor.setInstructorName(String.valueOf(instructorDto.getInstructorId()));
            instructor.setInstructorEmail(instructorDto.getInstructorEmail());
            instructorRepository.save(instructor);
        });
    }

    private void addAdmins(List<AdminDto> adminDtoList){
        adminDtoList.forEach(adminDto -> {
            Admin admin = new Admin();
            admin.setAdminName(adminDto.getAdminName());
            admin.setAdminEmail(adminDto.getAdminEmail());
            adminRepository.save(admin);
        });
    }

    private void mapCoursesToInstructors(List<CourseAndInstructorMappingDto> courseAndInstructorMappingDtos){
        courseAndInstructorMappingDtos.forEach(x -> {
            Course course = courseRepository.findById(x.getCourseId()).orElseThrow();
            Instructor instructor = instructorRepository.findById(x.getInstructorId()).orElseThrow();
            course.setCourseInstructor(instructor);
            courseRepository.save(course);
        });
    }

    private void mapCoursesToStudents(List<CourseAndStudentMappingDto> courseAndStudentMappingDtos){
        courseAndStudentMappingDtos.forEach(x -> {
            Course course = courseRepository.findById(x.getCourseId()).orElseThrow();
            x.getStudentIds().forEach(studentId -> {
                Student student = studentRepository.findById(studentId).orElseThrow();
                course.getStudents().add(student);
            });
            courseRepository.save(course);
        });
    }

    public void startup(StartupDto startupDto){
        this.addAdmins(startupDto.getAdmins());
        this.addStudents(startupDto.getStudents());
        this.addInstructors(startupDto.getInstructors());
        this.addCourses(startupDto.getCourses());
        this.mapCoursesToInstructors(startupDto.getCourseAndInstructorMappings());
        this.mapCoursesToStudents(startupDto.getCourseAndStudentMappings());
    }
}
