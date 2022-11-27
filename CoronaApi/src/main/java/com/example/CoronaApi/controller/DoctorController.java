package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.model.request.DoctorRequst;
import com.example.CoronaApi.model.response.Department;
import com.example.CoronaApi.model.response.Doctor;
import com.example.CoronaApi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping("/")
    public Collection<Doctor> getDoctorList() {
        Collection<Doctor> allDoctor = doctorRepository.getDoctorList();
        Collection<Doctor> response = new ArrayList<>();
//        for (Doctor doctor : allDoctor) {
//            doctor.add(linkTo(methodOn(DoctorController.class).getDoctorById(doctor.getDoctorId())).withSelfRel());
//            response.add(doctor);
//        }
        for (Doctor doctor : allDoctor){
            if (!doctor.hasLink("self")){
                doctor.add(linkTo(methodOn(DoctorController.class).getDoctorList()).withSelfRel().withType("get"));
            }
            if (!doctor.hasLink("Delete")){
                doctor.add(linkTo(methodOn(DoctorController.class).deleteDoctorById(doctor.getDoctorId())).withRel("Delete").withType("delete"));
            }
            if (!doctor.hasLink("update")){
                doctor.add(Link.of("/updateDoctor", "Update").withType("put"));
            }
            if (!doctor.hasLink("Patients")){
                doctor.add(linkTo(methodOn(PatientsController.class).getPatientList()).withRel("patients").withType("get"));
            }
            response.add(doctor);
        }
        return response;
    }

    @GetMapping("/{doctorId}")
    public Doctor getDoctorById(@PathVariable("doctorId") String doctorId) {
        Doctor doctor = doctorRepository.getDoctorById(doctorId);
        doctor.add(linkTo(methodOn(DoctorController.class).getDoctorById(doctorId)).withSelfRel());
        return doctor;
    }

    @PostMapping("/addDoctor")
    public GeneralResponse addDoctor(@RequestBody DoctorRequst patient) {
        return doctorRepository.addDoctor(patient);
    }

    @PutMapping("/updateDoctor")
    public GeneralResponse updateDoctor(@RequestBody DoctorRequst patient) {
        return doctorRepository.updateDoctor(patient);
    }

    @DeleteMapping("delete/{doctorId}")
    public GeneralResponse deleteDoctorById(@PathVariable("doctorId") String doctorId) {
        return doctorRepository.deleteDoctorById(doctorId);
    }


}
