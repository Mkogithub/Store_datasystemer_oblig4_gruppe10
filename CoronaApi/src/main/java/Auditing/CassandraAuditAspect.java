package Auditing;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CassandraAuditAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(CassandraAuditAspect.class);

    @Autowired
    private CassandraOperations cassandraOperations;

    @Around("@annotation(org.springframework.data.cassandra.core.mapping.PrimaryKey) || " +
            "@annotation(org.springframework.data.cassandra.core.mapping.Column) || " +
            "@annotation(org.springframework.data.cassandra.core.mapping.Table)")
    public Object audit(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed();
        LOGGER.info("Auditing method {} from class {}", methodName, className);
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                if (arg != null) {
                    cassandraOperations.insert(arg);
                }
            }
        }
        if (result != null) {
            cassandraOperations.insert(result);
        }
        return result;
    }

}