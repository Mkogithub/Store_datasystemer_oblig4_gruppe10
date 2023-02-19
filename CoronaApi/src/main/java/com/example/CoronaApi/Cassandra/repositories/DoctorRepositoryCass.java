package com.example.CoronaApi.Cassandra.repositories;

import com.example.CoronaApi.Cassandra.dataClasses.DoctorCass;
import org.springframework.data.repository.CrudRepository;

public interface DoctorRepositoryCass extends CrudRepository<DoctorCass, String> {

}
