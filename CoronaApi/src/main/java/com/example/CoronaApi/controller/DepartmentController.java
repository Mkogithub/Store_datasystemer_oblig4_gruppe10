package com.example.CoronaApi.controller;

import com.example.CoronaApi.Cassandra.dataClasses.DepartmentCass;
import com.example.CoronaApi.Cassandra.repositories.DepartmentRepositoryCass;
import com.example.CoronaApi.Cassandra.dataClasses.PatientCass;
import com.example.CoronaApi.Cassandra.repositories.PatientRepositoryCass;
import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.response.DepartmentResponse;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.model.response.Patient;
import com.example.CoronaApi.repository.DepartmentRepository;
import com.example.CoronaApi.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    DepartmentRepositoryCass departmentRepositoryCass;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PatientRepositoryCass patientRepositoryCass;

    @GetMapping("/")
    public Collection<DepartmentResponse> getAllDepartment() {
        List<DepartmentResponse> allDepartment = departmentRepository.getAllDepartment();
        Collection<DepartmentResponse> response = new ArrayList<>();
        for (DepartmentResponse department: allDepartment){
            DepartmentResponse departmentResponse = new DepartmentResponse(department.getDepartmentId(), department.getDepartmentName());
                //self
                departmentResponse.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(department.getDepartmentId())).withSelfRel().withType("get"));
                //delete
                departmentResponse.add(linkTo(methodOn(DepartmentController.class).deleteDepartment(department.getDepartmentId())).withRel("Delete").withType("delete"));
                //getAll
                departmentResponse.add(linkTo(methodOn(DepartmentController.class).getAllDepartment()).withRel("all").withType("get"));
                //getPatients
                departmentResponse.add(linkTo(methodOn(PatientsController.class).getPatientList()).withRel("patients").withType("get"));
            response.add(departmentResponse);
        }
        return response;
    }


    @GetMapping("/{departmentId}/patients")
    public Collection<Patient> getPatientsByDeptId(@PathVariable("departmentId") String departmentId) {
        Collection<PatientCass> allPatientsDept = patientRepository.getDepartmentPatients(departmentId); //rewrite this so that it returns a list of patients using patientRepository
        Collection<Patient> response = new ArrayList<>();
        for(PatientCass patient: allPatientsDept){
            Patient patient1 = new Patient(patient.getPatientId(), patient.getCreated(), patient.getPatientName(), patient.getModified(), patient.getDescription(), patient.getModified(), patient.getOtherSymptoms());
            //self
            patient1.add(linkTo(methodOn(DepartmentController.class).getPatientsByDeptId(patient1.getDepartmentId())).withSelfRel().withType("get"));
            //delete
            patient1.add(linkTo(methodOn(PatientsController.class).deletePatientById(patient1.getDepartmentId())).withRel("Delete").withType("delete"));

//            register symptoms
//            patient1.add(linkTo(methodOn(SymptomController.class).addPatientSymptom()).withRel("addPatientSymptom").withType("Post"));
        }
        return response;
    }


    @GetMapping("/{departmentId}")
    public DepartmentResponse getDepartmentById(@PathVariable("departmentId") String departmentId) {
        Iterable<DepartmentCass> oneDept = departmentRepositoryCass.findAllById(Collections.singleton(departmentId));
        DepartmentResponse response = null;
        if (departmentRepositoryCass.existsById(departmentId)){
            for(DepartmentCass dept: oneDept){
                DepartmentResponse departmentResponse1 = new DepartmentResponse(dept.getDepartmentId(), dept.getDepartmentName());
                departmentResponse1.add(linkTo(methodOn(DepartmentController.class).getDepartmentById(departmentResponse1.getDepartmentId())).withSelfRel().withType("get"));
                departmentResponse1.add(linkTo(methodOn(DepartmentController.class).deleteDepartment(departmentResponse1.getDepartmentId())).withRel("Delete").withType("delete"));
                departmentResponse1.add(linkTo(methodOn(DepartmentController.class).getAllDepartment()).withRel("all").withType("get"));
                departmentResponse1.add(linkTo(methodOn(DepartmentController.class).getPatientsByDeptId(departmentResponse1.getDepartmentId())).withRel("patients").withType("get"));
                response = departmentResponse1;
            }
        }
        return response;
    }

    @PostMapping(value = "/addDepartment")
    public GeneralResponse addDepartment(@RequestBody DepartmentRequest department) {
        return departmentRepository.addDepartment(department);
    }

    @DeleteMapping("delete/{departmentId}")
    public GeneralResponse deleteDepartment(@PathVariable("departmentId") String departmentId){
        return departmentRepository.deleteDepartment(departmentId);
    }
    @GetMapping("/getPatients/{departmentId}")
    public GeneralResponse getDepartmentPatients(@PathVariable("departmentId") String departmentId) {
//        Collection<PatientCass> allDepartmentPatients = patientRepository.getDepartmentPatients(departmentId);
//        Collection<Patient> response = new ArrayList<>();
//        for (PatientCass patient : allDepartmentPatients) {
//            response.add(patient);
//
//        }return response;
        return (GeneralResponse) patientRepository.getDepartmentPatients(departmentId);
}}
