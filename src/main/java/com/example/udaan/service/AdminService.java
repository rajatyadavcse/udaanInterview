package com.example.udaan.service;

import com.example.udaan.model.*;

public interface AdminService {
    public AdminResponse registerAdmin(User user);
    public CovidResultResponse updateCovidResult(CovidResult covidResult);
    public ZoneInfo getZoneInfo(Zone zone);
}
