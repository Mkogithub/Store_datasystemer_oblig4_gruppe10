package com.example.CoronaApi.Cassandra.dataClasses;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.hateoas.RepresentationModel;

@Table
public class DoctorCass {

    @PrimaryKey
    private String doctorId;
    private String departmentId;
    private String doctorName;

    public DoctorCass(String doctorId, String departmentId, String doctorName){
        this.doctorId=doctorId;
        this.departmentId=departmentId;
        this.doctorName=doctorName;
    }

    public DoctorCass() {

    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

}
