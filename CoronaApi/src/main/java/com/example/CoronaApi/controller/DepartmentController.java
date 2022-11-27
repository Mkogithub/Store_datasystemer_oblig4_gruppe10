package com.example.CoronaApi.controller;

import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.response.Department;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.model.response.Patient;
import com.example.CoronaApi.repository.DepartmentRepository;
import com.example.CoronaApi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/")
    public Collection<Department> getAllDepartment() {
        Collection<Department> allDepartment = departmentRepository.getAllDepartment();
        Collection<Department> response = new ArrayList<>();
        for (Department department: allDepartment){
            if (!department.hasLink("self")){
                department.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(department.getDepartmentId())).withSelfRel().withType("get"));
            }
            if (!department.hasLink("Delete")){
                department.add(linkTo(methodOn(DepartmentController.class).deleteDepartment(department.getDepartmentId())).withRel("Delete").withType("delete"));
            }
            if (!department.hasLink("all")){
                department.add(linkTo(methodOn(DepartmentController.class).getAllDepartment()).withRel("all").withType("get"));
            }
            if (!department.hasLink("Patients")){
                department.add(linkTo(methodOn(PatientsController.class).getPatientList()).withRel("patients").withType("get"));
            }
            response.add(department);
        }
        return response;
    }

    @GetMapping("/{departmentId}")
    public Department getDepartmentById(@PathVariable("departmentId") String departmentId) {
        Department department = departmentRepository.getDepartmentById(departmentId);
        if (!department.hasLink("self")){
            department.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(department.getDepartmentId())).withSelfRel().withType("get"));
        }
        if (!department.hasLink("Delete")){
            department.add(linkTo(methodOn(DepartmentController.class).deleteDepartment(department.getDepartmentId())).withRel("Delete").withType("delete"));
        }
        if (!department.hasLink("all")){
            department.add(linkTo(methodOn(DepartmentController.class).getAllDepartment()).withRel("all").withType("get"));
        }
        if (!department.hasLink("Patients")){
            department.add(linkTo(methodOn(PatientsController.class).getPatientList()).withRel("patients").withType("get"));
        }
        return department;
    }

    @PostMapping("/addDepartment")
    public GeneralResponse addDepartment(@RequestBody DepartmentRequest department) {
        return departmentRepository.addDepartment(department);
    }

    @DeleteMapping("delete/{departmentId}")
    public GeneralResponse deleteDepartment(@PathVariable("departmentId") String departmentId){
        return departmentRepository.deleteDepartment(departmentId);
    }
    @GetMapping("/getPatients/{departmentId}")
    public Collection<Patient> getDepartmentPatients(@PathVariable("departmentId") String departmentId) {
        Collection<Patient> allDepartmentPatients = patientRepository.getDepartmentPatients(departmentId);
        Collection<Patient> response = new ArrayList<>();
        for (Patient patient : allDepartmentPatients) {
            response.add(patient);
            if (!patient.hasLink("self")) {
                patient.add(linkTo(methodOn(PatientsController.class).getPatientById(patient.getPatientId())).withSelfRel());
            }

        }return response;
    }
}
