package com.example.CoronaApi.repository;

import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.response.Department;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class DepartmentRepository {
    private final static Map<String, Department> departmentMap = new HashMap<>();
    private int departmentId = 0;

    @Autowired
    private ObjectConverter objectConverter;
    public Department getDepartmentById(String patientId) {
        return departmentMap.get(patientId);
    }

    public Collection<Department> getAllDepartment() {
        return departmentMap.values();
    }

    public GeneralResponse addDepartment(DepartmentRequest department) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            departmentId++;
            department.setDepartmentId("d" + departmentId);
            departmentMap.put(department.getDepartmentId(), objectConverter.from(objectConverter.toJson(department), Department.class));
            generalResponse.setId("d" + departmentId);
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }

    public GeneralResponse deleteDepartment(String departmentId) {
        departmentMap.remove(departmentId);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(departmentId);
        generalResponse.setResult("Success");
        return generalResponse;    }

}
