package com.unifap.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Login controller class
@Controller
public class LoginController {

    // Login form action
    @GetMapping("/login")
    public String login() {
        return "auths/login";
    }
}