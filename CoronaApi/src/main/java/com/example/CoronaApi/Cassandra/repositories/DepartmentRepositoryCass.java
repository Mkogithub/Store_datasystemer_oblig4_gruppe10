package com.example.CoronaApi.Cassandra.repositories;

import com.example.CoronaApi.Cassandra.dataClasses.DepartmentCass;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepositoryCass extends CrudRepository<DepartmentCass, String> {

}
