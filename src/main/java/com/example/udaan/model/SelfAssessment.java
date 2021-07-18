package com.example.udaan.model;

import lombok.Data;

import java.util.List;

@Data
public class SelfAssessment {
    private String userId;
    private List<String> symptoms;
    private Boolean travelHistory;
    private Boolean contactWithCovidPatient;
}
