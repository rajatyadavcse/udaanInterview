package com.example.udaan.service;

import com.example.udaan.model.SelfAssessment;
import com.example.udaan.model.SelfAssessmentResponse;
import com.example.udaan.model.User;
import com.example.udaan.model.UserResponse;

public interface UserService {
    public UserResponse registerUser(User user);
    public SelfAssessmentResponse selfAssessment(SelfAssessment selfAssessment);

}