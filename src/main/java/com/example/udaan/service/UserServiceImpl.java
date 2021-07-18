package com.example.udaan.service;

import com.example.udaan.Dao.SelfAssessmentDAO;
import com.example.udaan.Dao.UserDAO;
import com.example.udaan.model.*;
import com.example.udaan.repository.SelfAssessmentRepository;
import com.example.udaan.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements  UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    SelfAssessmentRepository selfAssessmentRepository;

    @Override
    public UserResponse registerUser(User user) {
        if(StringUtils.isBlank(user.getName())||StringUtils.isBlank(user.getPhoneNumber())||StringUtils.isBlank(user.getPincode())) {
            //will throw an exception
        }
        UserDAO userDAO=new UserDAO();
        userDAO.setName(user.getName());
        userDAO.setPhoneNumber(user.getPhoneNumber());
        userDAO.setPinCode(user.getPincode());
        userDAO.setUserType("User");
        userDAO.setResult("negative");
        UserDAO response =userRepository.save(userDAO);
        UserResponse userResponse=new UserResponse();
        userResponse.setUserId(String.valueOf(response.getId()));
        return userResponse;


    }



    @Override
    public SelfAssessmentResponse selfAssessment(SelfAssessment selfAssessment) {
        if(StringUtils.isBlank(selfAssessment.getUserId()) || !userRepository.existsById(Integer.parseInt(selfAssessment.getUserId()))){
            //throw an exception
        }
        SelfAssessmentDAO selfAssessmentDAO=new SelfAssessmentDAO();
        String symptoms="";
        if(null!=selfAssessment.getSymptoms() && !selfAssessment.getSymptoms().isEmpty()){
            selfAssessment.getSymptoms().stream().forEach(symptom->{
                symptoms.concat(","+symptom);
            });
        }
        selfAssessmentDAO.setSymptoms(symptoms);
        selfAssessmentDAO.setTravelHistory(String.valueOf(selfAssessment.getTravelHistory()));
        selfAssessmentDAO.setContactWithCovidPatient(String.valueOf(selfAssessment.getContactWithCovidPatient()));
        //logic for calculation
        SelfAssessmentResponse response=new SelfAssessmentResponse();
        if((null==selfAssessment.getSymptoms() || selfAssessment.getSymptoms().isEmpty()) && Boolean.FALSE == selfAssessment.getTravelHistory() && Boolean.FALSE == selfAssessment.getContactWithCovidPatient()){
            response.setRiskPercentage(5);
            selfAssessmentDAO.setRiskPercentage(5);
        }if((Boolean.FALSE == selfAssessment.getTravelHistory() || Boolean.FALSE == selfAssessment.getContactWithCovidPatient())){
            if(null!=selfAssessment.getSymptoms() && selfAssessment.getSymptoms().size()==1){
                response.setRiskPercentage(50);
                selfAssessmentDAO.setRiskPercentage(50);
            }if(null!=selfAssessment.getSymptoms() && selfAssessment.getSymptoms().size()==2){

                response.setRiskPercentage(75);
                selfAssessmentDAO.setRiskPercentage(75);
            }if(null!=selfAssessment.getSymptoms() && selfAssessment.getSymptoms().size()>2){
                response.setRiskPercentage(95);
                selfAssessmentDAO.setRiskPercentage(95);
            }

        }

        selfAssessmentRepository.save(selfAssessmentDAO);

        return response;
    }
}
