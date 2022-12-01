package com.example.CoronaApi.controller;

import com.example.CoronaApi.DepartmentCass;
import com.example.CoronaApi.DepartmentRepositoryCass;
import com.example.CoronaApi.DoctorCass;
import com.example.CoronaApi.DoctorRepositoryCass;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.model.request.DoctorRequst;
import com.example.CoronaApi.model.response.DepartmentResponse;
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
    DoctorRepositoryCass doctorRepositoryCass;

    @GetMapping("/")
    public Collection<Doctor> getDoctorList() {
        Iterable<DoctorCass> allDepartment = doctorRepositoryCass.findAll();
        Collection<Doctor> response = new ArrayList<>();
        for (DoctorCass doctorCass: allDepartment){
            Doctor doctor = new Doctor(doctorCass.getDoctorId(), doctorCass.getDepartmentId(), doctorCass.getDoctorName());
            //self
            doctor.add(linkTo(methodOn(DoctorController.class).getDoctorList()).withSelfRel().withType("get"));
            //delete
            doctor.add(linkTo(methodOn(DoctorController.class).deleteDoctorById(doctor.getDoctorId())).withRel("Delete").withType("delete"));
            //getAll
            doctor.add(Link.of("/updateDoctor", "Update").withType("put"));
            //getPatients
            doctor.add(linkTo(methodOn(PatientsController.class).getPatientList()).withRel("patients").withType("get"));

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
