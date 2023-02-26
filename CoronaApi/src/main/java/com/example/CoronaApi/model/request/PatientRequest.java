package com.example.CoronaApi.model.request;

public class PatientRequest {

    public PatientRequest(String patientId, String created, String patientName, String modified, String description,String otherSymptoms,  String departmentId) {
        this.patientId = patientId;
        this.created = created;
        this.patientName = patientName;
        this.modified = modified;
        this.otherSymptoms = otherSymptoms;
        this.description = description;
        this.departmentId = departmentId;
    }

    private String patientId;
    private String created;
    private String patientName;
    private String modified;
    private String otherSymptoms;
    private String description;
    private String departmentId;

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

    public void setOtherSymptoms(String symptoms) {
        this.otherSymptoms = symptoms;
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
