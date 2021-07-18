package com.example.udaan.repository;

import com.example.udaan.Dao.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, Integer> {
    boolean existsByIdAndUserTypeContains(Integer id, String userType);
    UserDAO findOneById(Integer id);
    long countAllByPinCodeContainingAndResultContainingIgnoreCaseAndUserTypeContainingIgnoreCase(String pincode, String result, String userType);
}
