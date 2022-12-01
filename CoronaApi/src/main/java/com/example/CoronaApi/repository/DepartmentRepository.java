package com.example.CoronaApi.repository;

import com.example.CoronaApi.DepartmentCass;
import com.example.CoronaApi.DepartmentRepositoryCass;
import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.response.DepartmentResponse;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class DepartmentRepository {
    @Autowired
    public DepartmentRepositoryCass departmentRepositoryCass;
    private final static Map<String, DepartmentResponse> departmentMap = new HashMap<>();
    private int departmentId = 0;

    @Autowired
    private ObjectConverter objectConverter;
    public DepartmentResponse getDepartmentById(String departmentId) {
        try {
            return departmentMap.get(departmentId);
        } catch (Exception e) {
            return null;
        }

    }

    public Collection<DepartmentResponse> getAllDepartment() {
        return departmentMap.values();
    }

    public GeneralResponse addDepartment(DepartmentRequest department) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {

            departmentId++;
            department.setDepartmentId("d" + departmentId);
            String departmentId = department.getDepartmentId();
            DepartmentCass departmentCass = objectConverter.from(objectConverter.toJson(department), DepartmentCass.class);

            // departmentMap.put(department.getDepartmentId(), objectConverter.from(objectConverter.toJson(department), DepartmentResponse.class));
            departmentRepositoryCass.save(departmentCass);
            generalResponse.setId("d" + departmentId);
            generalResponse.setResult("Created");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }

    public GeneralResponse deleteDepartment(String departmentId) {
        departmentRepositoryCass.deleteById(departmentId);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(departmentId);
        generalResponse.setResult("Success, Deleted");
        return generalResponse;    }

}
