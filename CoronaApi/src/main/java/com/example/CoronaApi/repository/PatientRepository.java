package com.example.CoronaApi.repository;

import com.example.CoronaApi.Cassandra.dataClasses.PatientCass;
import com.example.CoronaApi.Cassandra.repositories.PatientRepositoryCass;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.model.request.PatientRequest;
import com.example.CoronaApi.model.response.DepartmentResponse;
import com.example.CoronaApi.model.response.Patient;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class PatientRepository {

    @Autowired
    private PatientRepositoryCass patientRepositoryCass;

    @Autowired
    private ObjectConverter objectConverter;

    public List<Patient> getAllPatients() {
        List<Patient> patientResponse = new ArrayList<>();
        Iterable<PatientCass> patientCasses = patientRepositoryCass.findAll();
        patientCasses.forEach(d -> patientResponse.add(objectConverter.from(objectConverter.toJson(d), Patient.class)));
        return patientResponse;
    }

    public Patient getPatientById(String patientId) {
        Optional<PatientCass> patientCass = patientRepositoryCass.findById(patientId);
        return patientCass.map(d -> objectConverter.from(objectConverter.toJson(d), Patient.class)).orElse(null);
    }

    public GeneralResponse deletePatientById(String patientId) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            patientRepositoryCass.deleteById(patientId);
            generalResponse.setId(patientId);
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }

    public Collection<PatientCass> getDepartmentPatients(String DepartmentId) {
        return (Collection<PatientCass>) patientRepositoryCass.findAllByDepartmentId(DepartmentId);
    }

    public GeneralResponse addPatient(PatientRequest patientRequest) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            PatientCass patient = new PatientCass();
            patient.setPatientName(patientRequest.getPatientName());
            patient.setDescription(patientRequest.getDescription());
            patient.setDepartmentId(patientRequest.getDepartmentId());
            patient.setCreated(LocalDateTime.now().toString());
            patient.setModified(LocalDateTime.now().toString());
            PatientCass savedPatient = patientRepositoryCass.save(patient);
            generalResponse.setId(savedPatient.getPatientId());
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }

    public GeneralResponse updatePatient(PatientRequest patientRequest) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            PatientCass patient = patientRepositoryCass.findById(patientRequest.getPatientId()).orElse(null);
            if (patient != null) {
                patient.setPatientName(patientRequest.getPatientName());
                patient.setDescription(patientRequest.getDescription());
                patient.setDepartmentId(patientRequest.getDepartmentId());
                patient.setModified(LocalDateTime.now().toString());
                PatientCass savedPatient = patientRepositoryCass.save(patient);
                generalResponse.setId(savedPatient.getPatientId());
                generalResponse.setResult("Success");
            } else {
                generalResponse.setId(String.valueOf(-1));
                generalResponse.setResult("Patient not found");
            }
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }
}