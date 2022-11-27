package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.model.response.Patient;
import com.example.CoronaApi.model.request.PatientRequest;
import com.example.CoronaApi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/patients")
public class PatientsController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public Collection<Patient> getPatientList(){
        Collection<Patient> allPatient = patientRepository.getPatientList();
        Collection<Patient> response = new ArrayList<>();
        for (Patient patient: allPatient){
            response.add(patient);
            if(!patient.hasLink("self"))
                patient.add(linkTo(methodOn(PatientsController.class).getPatientById(patient.getPatientId())).withSelfRel());
        }
        return response;
    }

    @GetMapping("/{patientId}")
    public Patient getPatientById(@PathVariable("patientId") String patientId){
        Patient patient = patientRepository.getPatientById(patientId);
        patient.add(linkTo(methodOn(PatientsController.class).getPatientById(patientId)).withSelfRel());
        return patient;
    }

    @PostMapping("/addPatient")
    public GeneralResponse addPatient(@RequestBody PatientRequest patient){
        return patientRepository.addPatient(patient);
    }

    @PutMapping("/updatePatient")
    public GeneralResponse updatePatient(@RequestBody PatientRequest patient){
        return patientRepository.updatePatient(patient);
    }

    @DeleteMapping("delete/{patientId}")
    public GeneralResponse deletePatientById(@PathVariable("patientId") String patientId){
        return patientRepository.deletePatientById(patientId);
    }


}

