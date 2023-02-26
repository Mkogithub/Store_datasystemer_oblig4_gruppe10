package com.example.CoronaApi.repository;

import com.example.CoronaApi.Cassandra.dataClasses.DepartmentCass;
import com.example.CoronaApi.Cassandra.repositories.DepartmentRepositoryCass;
import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.response.DepartmentResponse;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DepartmentRepository {

    @Autowired
    public DepartmentRepositoryCass departmentRepositoryCass;

    @Autowired
    private ObjectConverter objectConverter;

    public DepartmentResponse getDepartmentById(String departmentId) {
        Optional<DepartmentCass> departmentCass = departmentRepositoryCass.findById(departmentId);
        return departmentCass.map(d -> objectConverter.from(objectConverter.toJson(d), DepartmentResponse.class)).orElse(null);
    }

    public List<DepartmentResponse> getAllDepartment() {
        List<DepartmentResponse> departmentResponses = new ArrayList<>();
        Iterable<DepartmentCass> departmentCasses = departmentRepositoryCass.findAll();
        departmentCasses.forEach(d -> departmentResponses.add(objectConverter.from(objectConverter.toJson(d), DepartmentResponse.class)));
        return departmentResponses;
    }

    public GeneralResponse addDepartment(DepartmentRequest department) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            DepartmentCass departmentCass = new DepartmentCass();
            departmentCass.setDepartmentId("d" + departmentRepositoryCass.count());
            departmentCass.setDepartmentName(department.getDepartmentName());
            departmentRepositoryCass.save(departmentCass);
            generalResponse.setId(departmentCass.getDepartmentId());
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
        return generalResponse;
    }
}
