package com.esd.assignment.controllers;

import com.esd.assignment.entities.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.esd.assignment.services.AdminService;
import com.esd.assignment.services.CourseService;

import java.util.List;

@RestController
@RequestMapping(path = "api/admin")
public class AdminController {
    private final AdminService adminService;
    private final CourseService courseService;

    public AdminController(AdminService adminService, CourseService courseService) {
        this.adminService = adminService;
        this.courseService = courseService;
    }

    @GetMapping(path = "/getAllCourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping(path = "/courses/{courseId}/{instructorId}")
    public ResponseEntity<String> updateCourseInstructor(@PathVariable Long courseId ,@PathVariable Long instructorId) {
        courseService.updateCourseInstructor(courseId,instructorId);
        return ResponseEntity.ok("Instructor Name updated successfully");
    }

    @DeleteMapping(path = "/courses/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable String courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully");
    }
}
