package com.example.CoronaApi.Cassandra.repositories;

import com.example.CoronaApi.Cassandra.dataClasses.PatientCass;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepositoryCass extends CrudRepository<PatientCass, String> {

    Iterable<PatientCass> findAllByDepartmentId(String departmentId);
}
