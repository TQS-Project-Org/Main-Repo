package tqs.amanacu.picky.controllers;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.amanacu.picky.payload.request.LoginRequest;
import tqs.amanacu.picky.payload.request.SignupRequest;
import tqs.amanacu.picky.payload.response.UserInfoResponse;
import tqs.amanacu.picky.payload.response.MessageResponse;
import tqs.amanacu.picky.security.JwtUtils;
import tqs.amanacu.picky.security.UserDetailsImpl;

import tqs.amanacu.picky.services.AuthorizationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {

        UserDetailsImpl userDetails = authorizationService.login(loginRequest.getUsername(), loginRequest.getPassword());

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return ResponseEntity.ok()
            .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
            .body(new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        
        boolean res = authorizationService.register(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getEmail(), signUpRequest.getRole());

        if (!res) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email or username already in use!"));
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new MessageResponse("You've been signed out!"));
    }
}
