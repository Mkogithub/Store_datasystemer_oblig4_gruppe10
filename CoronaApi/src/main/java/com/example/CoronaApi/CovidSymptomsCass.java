package com.example.CoronaApi;


import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class CovidSymptomsCass {
    @PrimaryKey
    private String id;
    private String patientId;
    private String visitingDate;
    private Boolean coughing;
    private Boolean fever;

    public CovidSymptomsCass(String id, String patientId, String visitingDate, boolean coughing, boolean fever){
        this.id = id;
        this.patientId = patientId;
        this.visitingDate = visitingDate;
        this.coughing = coughing;
        this.fever = fever;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitingDate() {
        return visitingDate;
    }

    public void setVisitingDate(String visitingDate) {
        this.visitingDate = visitingDate;
    }

    public Boolean getCoughing() {
        return coughing;
    }

    public void setCoughing(Boolean coughing) {
        this.coughing = coughing;
    }

    public Boolean getFever() {
        return fever;
    }

    public void setFever(Boolean fever) {
        this.fever = fever;
    }
}
