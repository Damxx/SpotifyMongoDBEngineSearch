package com.example.spotify;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfo {

        @GetMapping("/login")
        public Principal get(Principal principal){
            return principal;
        }

        @GetMapping("/spotify")
        public Principal getAll(Principal principal){
            return principal;
        }



}
