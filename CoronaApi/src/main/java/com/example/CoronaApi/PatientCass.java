package com.example.CoronaApi;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class PatientCass {

    @PrimaryKey
    private String patientId;
    private String created;
    private String patientName;
    private String modified;
    private String description;
    private String otherSymptoms;
    private String departmentId;

    public PatientCass(String patientId, String created, String patientName, String modified, String description,String otherSymptoms,  String departmentId) {
        this.patientId = patientId;
        this.created = created;
        this.patientName = patientName;
        this.modified = modified;
        this.description = description;
        this.otherSymptoms = otherSymptoms;
        this.departmentId = departmentId;
    }



    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getOtherSymptoms() {
        return otherSymptoms;
    }

    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }
}
