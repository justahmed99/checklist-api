package com.example.checklistapi.controller;

import com.example.checklistapi.dto.request.JwtRequest;
import com.example.checklistapi.dto.request.RegisterRequest;
import com.example.checklistapi.dto.response.DataResponse;
import com.example.checklistapi.dto.response.JwtResponse;
import com.example.checklistapi.persistence.pojo.UserChecklist;
import com.example.checklistapi.persistence.repository.UserRepository;
import com.example.checklistapi.persistence.service.implementation.JwtUserDetailsService;
import com.example.checklistapi.utils.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<DataResponse> registerUser(@RequestBody RegisterRequest request) throws Exception {
        UserChecklist userChecklist = new UserChecklist();
        userChecklist.setUsername(request.getUsername());
        userChecklist.setEmail(request.getEmail());
        userChecklist.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        repository.save(userChecklist);
        return new ResponseEntity<>(new DataResponse(null, "User created"), HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
