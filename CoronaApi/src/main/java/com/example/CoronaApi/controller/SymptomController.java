package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.request.CovidSymptomsRequest;
import com.example.CoronaApi.model.response.CovidSymptoms;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/symptoms")
public class SymptomController {
    @Autowired
    private SymptomRepository symptomRepository;

    @GetMapping("/")
    public Collection<CovidSymptoms> getAllCovidSymptoms() {
        Collection<CovidSymptoms> allCovidSymptoms = symptomRepository.getAllPatientSymptom();
        Collection<CovidSymptoms> response = new ArrayList<>();
        for (CovidSymptoms covidSymptoms: allCovidSymptoms){
            covidSymptoms.add(linkTo(methodOn(SymptomController.class).getCovidSymptomsById(covidSymptoms.getId())).withSelfRel());
            response.add(covidSymptoms);
        }
        return response;
    }

    @GetMapping("/{patientId}")
    public CovidSymptoms getCovidSymptomsById(@PathVariable("patientId") String patientId) {
        CovidSymptoms covidSymptoms = symptomRepository.getSymptomPatientById(patientId);
        covidSymptoms.add(linkTo(methodOn(SymptomController.class).getCovidSymptomsById(patientId)).withSelfRel());
        return covidSymptoms;
    }


    @PostMapping("/addSymptoms")
    public GeneralResponse addPatientSymptom(@RequestBody CovidSymptomsRequest covidSymptoms) {
        return symptomRepository.addPatientSymptom(covidSymptoms);
    }

    @DeleteMapping("delete/{patientId}")
    public GeneralResponse deletePatientSymptom(@PathVariable("patientId") String patientId){
        return symptomRepository.deletePatientSymptom(patientId);
    }

}
