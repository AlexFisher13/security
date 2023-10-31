package com.fisher.securityapp.controller;

import com.fisher.securityapp.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/showUser")
    public String showUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails principal = (PersonDetails) auth.getPrincipal();
        System.out.println(principal.getPerson());

        return "hello";
    }
}
