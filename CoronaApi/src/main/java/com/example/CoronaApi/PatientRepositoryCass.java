package com.example.CoronaApi;

import org.springframework.data.repository.CrudRepository;

public interface PatientRepositoryCass extends CrudRepository<PatientCass, String> {

    Iterable<PatientCass> findAllByDepartmentId(String departmentId);
}
