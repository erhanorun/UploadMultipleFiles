package com.project.UploadMultipleFiles.controllers;

import com.project.UploadMultipleFiles.entities.UserEntity;
import com.project.UploadMultipleFiles.repositories.UserRepository;
import com.project.UploadMultipleFiles.requests.LoginRequest;
import com.project.UploadMultipleFiles.requests.SignUpRequest;
import com.project.UploadMultipleFiles.responses.MessageResponse;
import com.project.UploadMultipleFiles.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class Authorization {
    AuthenticationManager authenticate;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    JwtUtils jwtUtils;

    public Authorization(AuthenticationManager authenticate, UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
        this.authenticate = authenticate;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String generateToken(@Valid @RequestBody LoginRequest authRequest) throws Exception {
        try {
            authenticate.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        return jwtUtils.generateToken(authRequest.getUsername());
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {


        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        UserEntity userEntity = new UserEntity(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        userRepository.save(userEntity);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
