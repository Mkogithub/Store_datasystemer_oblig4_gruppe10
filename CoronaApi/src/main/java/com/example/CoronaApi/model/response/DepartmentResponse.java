package com.example.CoronaApi.model.response;

import org.springframework.hateoas.RepresentationModel;

public class DepartmentResponse extends RepresentationModel<DepartmentResponse> {
    private String departmentId;
    private String departmentName;

    public DepartmentResponse(String departmentId, String departmentName){
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
