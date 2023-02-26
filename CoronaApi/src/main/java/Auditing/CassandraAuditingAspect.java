package Auditing;
import com.example.CoronaApi.Cassandra.dataClasses.AuditEvent;
import com.example.CoronaApi.repository.AuditEventRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Aspect
@Component
public class CassandraAuditingAspect {

    @Autowired
    private AuditEventRepository auditEventRepository;

    @Autowired
    private CurrentUserProvider currentUserProvider;

    @AfterReturning(pointcut = "@annotation(audit)", returning = "result")
    public void logAuditEvent(JoinPoint joinPoint, Audit audit, Object result) {
        // create an AuditEvent object with data from the annotation and method arguments
        AuditEvent auditEvent = new AuditEvent(UUID.randomUUID(), LocalDateTime.now(), audit.operation(), currentUserProvider.getCurrentUsername());

        // save the audit event to the database
        auditEventRepository.save(auditEvent);
    }

    // helper methods for getting the current user's username, etc.

}