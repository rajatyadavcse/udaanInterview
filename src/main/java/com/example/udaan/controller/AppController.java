package com.example.udaan.controller;

import com.example.udaan.model.*;
import com.example.udaan.service.AdminService;
import com.example.udaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;


    @PostMapping("user")
    public UserResponse registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("admin")
    public AdminResponse registerAdmin(@RequestBody User user){
        return adminService.registerAdmin(user);
    }
    @PostMapping("user/selfAssessment")
    public SelfAssessmentResponse selfAssessment(@RequestBody SelfAssessment selfAssessment){
        return userService.selfAssessment(selfAssessment);
    }
    @PostMapping("covid/result")
    public CovidResultResponse updateCovidResult(@RequestBody CovidResult covidResult){
        return adminService.updateCovidResult(covidResult);
    }
    @PostMapping("zone/info")
    public ZoneInfo getZoneInfo(Zone zone){
        return adminService.getZoneInfo(zone);
    }
}
