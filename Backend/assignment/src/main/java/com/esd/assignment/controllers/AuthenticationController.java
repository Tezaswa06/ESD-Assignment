package com.esd.assignment.controllers;

import com.esd.assignment.dto.LoginRequest;
import com.esd.assignment.dto.LoginResponse;
import com.esd.assignment.dto.RegisterRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esd.assignment.services.AuthenticationService;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/registerAdmin")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest registerRequest) {
        return new ResponseEntity<>(authenticationService.registerAdmin(registerRequest), HttpStatus.OK);
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginAdmin = authenticationService.loginAdmin(loginRequest);
        HttpStatus status = loginAdmin.getAdminId() != null ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(loginAdmin,status);
    }
}