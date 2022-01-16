package com.natnael.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public String regestrationForm() {
        return "registration";
    }

}
