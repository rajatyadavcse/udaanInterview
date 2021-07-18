package com.example.udaan.service;

import com.example.udaan.Dao.UserDAO;
import com.example.udaan.model.*;
import com.example.udaan.repository.SelfAssessmentRepository;
import com.example.udaan.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    SelfAssessmentRepository selfAssessmentRepository;
    @Override
    public AdminResponse registerAdmin(User user) {
        if(StringUtils.isBlank(user.getName())||StringUtils.isBlank(user.getPhoneNumber())||StringUtils.isBlank(user.getPincode())) {
            //will throw an exception
        }
        UserDAO userDAO=new UserDAO();
        userDAO.setName(user.getName());
        userDAO.setPhoneNumber(user.getPhoneNumber());
        userDAO.setPinCode(user.getPincode());
        userDAO.setUserType("Admin");
        userDAO.setResult("negative");
        UserDAO response =userRepository.save(userDAO);
        AdminResponse adminResponse=new AdminResponse();
        adminResponse.setAdminId(String.valueOf(response.getId()));
        return adminResponse;
    }

    @Override
    public CovidResultResponse updateCovidResult(CovidResult covidResult) {
        if(StringUtils.isBlank(covidResult.getAdminId())||StringUtils.isBlank(covidResult.getUserId())||StringUtils.isBlank(covidResult.getResult())) {
            //will throw an exception
        }
        if(userRepository.existsByIdAndUserTypeContains(Integer.parseInt(covidResult.getAdminId()), "Admin")){
            if(userRepository.existsById(Integer.parseInt(covidResult.getUserId()))){
                 UserDAO userDAO=userRepository.findOneById(Integer.parseInt(covidResult.getUserId()));
                 userDAO.setResult(covidResult.getResult());
                 userRepository.save(userDAO);
            }
        }else{
            //throw an exception
        }
        CovidResultResponse response=new CovidResultResponse();
        response.setUpdated(true);
        return response;
    }

    @Override
    public ZoneInfo getZoneInfo(Zone zone) {
        if(StringUtils.isBlank(zone.getPinCode())){

        }
        ZoneInfo zoneInfo=new ZoneInfo();
        long count = userRepository.countAllByPinCodeContainingAndResultContainingIgnoreCaseAndUserTypeContainingIgnoreCase(zone.getPinCode(), "positive", "User");
        if(count==0){
            zoneInfo.setNumCases(String.valueOf(count));
            zoneInfo.setZoneType(ZoneType.GREEN);
        }
        else if(count>0 && count<5)
        {
            zoneInfo.setNumCases(String.valueOf(count));
            zoneInfo.setZoneType(ZoneType.ORANGE);
        }else{
            zoneInfo.setNumCases(String.valueOf(count));
            zoneInfo.setZoneType(ZoneType.RED);
        }

        return zoneInfo;
    }
}
