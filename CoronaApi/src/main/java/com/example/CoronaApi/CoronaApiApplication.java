package com.example.CoronaApi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@EnableSwagger2
@SpringBootApplication
public class CoronaApiApplication {

	private final static Logger log = LoggerFactory.getLogger(CoronaApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoronaApiApplication.class, args);
	}
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.example.CoronaApi")).build();
//	}

	@Bean
	public CommandLineRunner clr(PatientRepositoryCass patientRepositoryCass) {
		return args -> {
			patientRepositoryCass.deleteAll();



			DoctorCass doctor1 = new DoctorCass("d1", "d1", "Joel Odinaka");
			DoctorCass doctor2 = new DoctorCass("d2", "d2", "Lege Legesen");
			PatientCass patient1 = new PatientCass("p1", "27-11-2022", "John Deere", "27-11-2022", "Sore Throat", "Sore Throat", "d1");
			PatientCass patient2 = new PatientCass("p2", "27-11-2022", "Jane Doe", "27-11-2022", "Sore Throat", "Sore Throat, bad breath", "d1");




			PatientCass savedPatient1 = patientRepositoryCass.save(patient1);
			PatientCass savedPatient2 = patientRepositoryCass.save(patient2);

			patientRepositoryCass.findAll()
					.forEach(v -> log.info("Patient: {}", v.getPatientName()));

			patientRepositoryCass.findById(savedPatient1.getPatientId())
					.ifPresent(v -> log.info("Patient by id: {}", v.getPatientName()));

		};
	}

}
