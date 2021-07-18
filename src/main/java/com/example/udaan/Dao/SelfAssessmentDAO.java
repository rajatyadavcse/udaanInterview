package com.example.udaan.Dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "SELF_ASSESSMENT")
public class SelfAssessmentDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id")
    private UserDAO userId;

    private String symptoms;

    private String travelHistory;

    private String contactWithCovidPatient;

    private Integer riskPercentage;

}
