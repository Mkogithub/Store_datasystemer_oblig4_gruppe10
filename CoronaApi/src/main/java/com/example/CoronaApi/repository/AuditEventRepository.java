package com.example.CoronaApi.repository;

import com.example.CoronaApi.Cassandra.dataClasses.AuditEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditEventRepository extends CassandraRepository<AuditEvent, UUID> {
}