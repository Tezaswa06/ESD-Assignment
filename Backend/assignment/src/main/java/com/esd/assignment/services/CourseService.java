package com.esd.assignment.services;

import com.esd.assignment.entities.Course;
import com.esd.assignment.entities.Instructor;
import com.esd.assignment.entities.Student;
import com.esd.assignment.repository.InstructorRepository;
import org.springframework.stereotype.Service;
import com.esd.assignment.repository.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private InstructorRepository instructorRepository;

    public CourseService(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public void updateCourseInstructor(String courseId,String instructorName) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Instructor instructor = instructorRepository.findById(instructorName)
                .orElseThrow(() -> new RuntimeException("Instructor not found with name: " + instructorName));

        course.setInstructor(instructor);
        courseRepository.save(course);
    }

    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        for(Student student : course.getStudents()) {
            student.getCourses().removeIf(c -> c.getCourseId().equals(courseId));
        }

        Instructor instructor = course.getInstructor();
        if(instructor != null) {
            instructor.getCourses().removeIf(c -> c.getCourseId().equals(courseId));
        }
        courseRepository.delete(course);
    }

    public List<Course> getCoursesWithRegisteredStudents(){
        return courseRepository.findByStudentsNotEmpty();
    }


}
