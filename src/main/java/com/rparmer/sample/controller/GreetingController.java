package com.rparmer.sample.controller;

import com.rparmer.sample.model.GithubUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @GetMapping("/secured")
    public String secured(Authentication authentication) {
        return String.format("Hello %s", ((GithubUser) authentication.getDetails()).getName());
    }

    @GetMapping("/unsecured")
    public String unsecured() {
        return "Hello from Unsecured";
    }

    @GetMapping("/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
