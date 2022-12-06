package com.example.x3.MovieManagementApp.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

@Controller
@ApiIgnore
public class HomeController {

    @RequestMapping("")
    @ApiOperation(value = "Homepage endpoint")
    public String displayHomepage(){
        return "index.html";
    }
}
