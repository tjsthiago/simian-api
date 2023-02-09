package com.simian.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimianController {

    @GetMapping("/simian")
    public String isSimian() {
        return "It is working";
    }

}
