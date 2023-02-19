package com.example.CoronaApi.Cassandra.dataClasses;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.hateoas.RepresentationModel;

@Table
public class DepartmentCass{
    @PrimaryKey
    private String departmentId;
    private String departmentName;

    public DepartmentCass(String departmentId, String departmentName){
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public DepartmentCass() {
        String departmentId;
        String departmentName;
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
