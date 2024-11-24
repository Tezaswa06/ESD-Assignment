package com.esd.assignment.TestControllers;
import com.esd.assignment.controllers.AuthenticationController;
import com.esd.assignment.dto.LoginRequest;
import com.esd.assignment.dto.LoginResponse;
import com.esd.assignment.dto.RegisterRequest;
import com.esd.assignment.services.AuthenticationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void testRegisterAdmin() throws Exception {
        // Mock request and service response
        RegisterRequest registerRequest = new RegisterRequest("helllo@example.com", "hello","hello","1234145121");
        String mockResponse = "Admin registered successfully";

        when(authenticationService.registerAdmin(any(RegisterRequest.class))).thenReturn(mockResponse);

        // Perform the test
        mockMvc.perform(post("/api/auth/registerAdmin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"admin\",\"email\":\"admin@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string(mockResponse));

        // Verify service interaction
        verify(authenticationService, times(1)).registerAdmin(any(RegisterRequest.class));
    }

    @Test
    void testLoginAdminSuccess() throws Exception {
        // Mock request and service response
        LoginRequest loginRequest = new LoginRequest("hello@example.com", "hello");
        LoginResponse mockResponse = new LoginResponse("Login Successful", "1");

        when(authenticationService.loginAdmin(any(LoginRequest.class))).thenReturn(mockResponse);

        // Perform the test
        mockMvc.perform(post("/api/auth/loginAdmin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.adminId").value(1L))
                .andExpect(jsonPath("$.message").value("Login Successful"));

        // Verify service interaction
        verify(authenticationService, times(1)).loginAdmin(any(LoginRequest.class));
    }

    @Test
    void testLoginAdminFailure() throws Exception {
        // Mock request and service response
        LoginRequest loginRequest = new LoginRequest("hello@example.com", "wrongpassword");
        LoginResponse mockResponse = new LoginResponse( "Login Failed", null);

        when(authenticationService.loginAdmin(any(LoginRequest.class))).thenReturn(mockResponse);

        // Perform the test
        mockMvc.perform(post("/api/auth/loginAdmin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"admin@example.com\",\"password\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.adminId").doesNotExist());

        // Verify service interaction
        verify(authenticationService, times(1)).loginAdmin(any(LoginRequest.class));
    }
}
