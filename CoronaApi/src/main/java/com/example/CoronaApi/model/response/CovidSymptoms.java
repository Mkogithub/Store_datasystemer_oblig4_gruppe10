package com.example.CoronaApi.model.response;


import org.springframework.hateoas.RepresentationModel;

public class CovidSymptoms extends RepresentationModel<CovidSymptoms> {
    private String id;
    private String patientId;
    private String visitingDate;
    private Boolean coughing;
    private Boolean fever;

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
