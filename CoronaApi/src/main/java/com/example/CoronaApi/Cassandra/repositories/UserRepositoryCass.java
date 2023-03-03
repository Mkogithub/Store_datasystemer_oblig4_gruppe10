package com.example.CoronaApi.Cassandra.repositories;

import com.example.CoronaApi.Cassandra.dataClasses.UserCass;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryCass extends CassandraRepository<UserCass, UUID> {

    @Query("SELECT * FROM users WHERE data.email = ?0")
    Optional<User> findByUsername(String username);

    @Query("SELECT * FROM users WHERE data.role = ?0")
    List<UserCass> findByRole(String role);
}
