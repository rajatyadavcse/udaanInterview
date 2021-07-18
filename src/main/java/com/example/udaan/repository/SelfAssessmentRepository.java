package com.example.udaan.repository;

import com.example.udaan.Dao.SelfAssessmentDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelfAssessmentRepository extends JpaRepository<SelfAssessmentDAO, Integer> {
}
