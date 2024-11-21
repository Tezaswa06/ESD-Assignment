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

    public void updateCourseInstructor(Long courseId, Long instructorId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new RuntimeException("Instructor not found with name: " + instructorId));

        course.setCourseInstructor(instructor);
        courseRepository.save(course);
    }


    public void deleteCourse(String courseId) {
        Course course = courseRepository.findById(Long.valueOf(courseId))
                .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseId));

        // Remove the course from all students' course lists
        for (Student student : course.getStudents()) {
            student.getCourses().removeIf(c -> c.getCourseId().equals(Long.valueOf(courseId)));
        }

        // Remove the course from the instructor's course list
        Instructor instructor = course.getCourseInstructor(); // Ensure field name matches
        if (instructor != null) {
            instructor.getCourses().removeIf(c -> c.getCourseId().equals(Long.valueOf(courseId)));
        }

        // Delete the course
        courseRepository.delete(course);
    }



}
