package com.esd.assignment.TestControllers;

import com.esd.assignment.controllers.AdminController;
import com.esd.assignment.entities.Course;
import com.esd.assignment.services.AdminService;
import com.esd.assignment.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Mock
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllCourses() {
        // Mock data
        List<Course> courses = new ArrayList<>();
        Course course1 = new Course();
        course1.setCourseName("Mathematics 101");

        Course course2 = new Course();
        course2.setCourseName("Physics 101");

        courses.add(course1);
        courses.add(course2);

        // Mock service
        when(courseService.getAllCourses()).thenReturn(courses);

        // Call the method
        ResponseEntity<List<Course>> response = adminController.getAllCourses();

        // Verify and assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        verify(courseService, times(1)).getAllCourses();
    }

    @Test
    void testUpdateCourseInstructor() {
        // Mock service
        doNothing().when(courseService).updateCourseInstructor(1L, 2L);

        // Call the method
        ResponseEntity<String> response = adminController.updateCourseInstructor(1L, 2L);

        // Verify and assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Instructor Name updated successfully", response.getBody());
        verify(courseService, times(1)).updateCourseInstructor(1L, 2L);
    }

    @Test
    void testDeleteCourse() {
        // Mock service
        doNothing().when(courseService).deleteCourse("1");

        // Call the method
        ResponseEntity<String> response = adminController.deleteCourse("1");

        // Verify and assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Course deleted successfully", response.getBody());
        verify(courseService, times(1)).deleteCourse("1");
    }
}
