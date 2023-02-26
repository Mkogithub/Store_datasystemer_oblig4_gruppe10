package com.example.CoronaApi.Cassandra.dataClasses;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("audit_events")
public class AuditEvent {

    @PrimaryKey
    private UUID id;

    @Column("timestamp")
    private LocalDateTime timestamp;

    @Column("operation")
    private String operation;

    @Column("username")
    private String username;

    public AuditEvent(UUID randomUUID, LocalDateTime now, String operation, String currentUsername) {
        this.id = randomUUID;
        this.timestamp = now;
        this. operation = operation;
        this.username = currentUsername;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
