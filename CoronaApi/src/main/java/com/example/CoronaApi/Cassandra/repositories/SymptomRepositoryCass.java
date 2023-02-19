package com.example.CoronaApi.Cassandra.repositories;

import com.example.CoronaApi.Cassandra.dataClasses.CovidSymptomsCass;
import org.springframework.data.repository.CrudRepository;

public interface SymptomRepositoryCass extends CrudRepository<CovidSymptomsCass, String> {

}
