package com.example.x3.MovieManagementApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("")
    public String displayHomepage(){
        return "index";
    }
}
