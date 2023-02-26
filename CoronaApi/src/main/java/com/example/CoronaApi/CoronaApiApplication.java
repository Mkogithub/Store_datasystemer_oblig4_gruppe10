package com.example.CoronaApi;


import com.example.CoronaApi.Cassandra.dataClasses.CovidSymptomsCass;
import com.example.CoronaApi.Cassandra.dataClasses.DepartmentCass;
import com.example.CoronaApi.Cassandra.dataClasses.DoctorCass;
import com.example.CoronaApi.Cassandra.dataClasses.PatientCass;
import com.example.CoronaApi.Cassandra.repositories.DepartmentRepositoryCass;
import com.example.CoronaApi.Cassandra.repositories.DoctorRepositoryCass;
import com.example.CoronaApi.Cassandra.repositories.PatientRepositoryCass;
import com.example.CoronaApi.Cassandra.repositories.SymptomRepositoryCass;
import com.example.CoronaApi.model.request.DepartmentRequest;
import com.example.CoronaApi.model.request.DoctorRequst;
import com.example.CoronaApi.model.request.PatientRequest;
import com.example.CoronaApi.repository.DepartmentRepository;
import com.example.CoronaApi.repository.DoctorRepository;
import com.example.CoronaApi.repository.PatientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
//@EnableSwagger2
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
	//commandlinerunner for å initialisere, og legge inn eksempeldata
	@Bean
	public CommandLineRunner clr(PatientRepositoryCass patientRepositoryCass,
								 DoctorRepositoryCass doctorRepositoryCass,
								 DepartmentRepositoryCass departmentRepositoryCass,
								 SymptomRepositoryCass symptomRepositoryCass,
								 PatientRepository patientRepository,
								 DepartmentRepository departmentRepository,
								 DoctorRepository doctorRepository) {
		return args -> {
			patientRepositoryCass.deleteAll();


			DepartmentCass dept1 = new DepartmentCass("d1", "Akutten");
			DepartmentCass dept2 = new DepartmentCass("d2", "legevakten");

			DepartmentRequest dept3 = new DepartmentRequest("d1", "Akutten");
			DepartmentRequest dept4 = new DepartmentRequest("d2", "Legevakten");

			DoctorCass doctor1 = new DoctorCass("d1", "d1", "Joel Odinaka");
			DoctorCass doctor2 = new DoctorCass("d2", "d2", "Lege Legesen");

			DoctorRequst doctor3 = new DoctorRequst("d1", "d1", "Joel Odinaka");
			DoctorRequst doctor4 = new DoctorRequst("d2", "d2", "Lege Legesen");

			PatientCass patient1 = new PatientCass("p1", "27-11-2022", "John Deere", "27-11-2022", "Sore Throat", "Sore Throat", "d1");
			PatientCass patient2 = new PatientCass("p2", "27-11-2022", "Jane Doe", "27-11-2022", "Sore Throat", "Sore Throat, bad breath", "d1");
			PatientRequest patient3 = new PatientRequest("p1", "27-11-2022", "John Deere", "27-11-2022", "Sore Throat", "Sore Throat", "d1");
			PatientRequest patient4 = new PatientRequest("p2", "27-11-2022", "Jane Doe", "27-11-2022", "Sore Throat", "Sore Throat, bad breath", "d1");

			CovidSymptomsCass covidSymptomsCass1 = new CovidSymptomsCass("1", "p1", "27-11-2022", true, false);
			CovidSymptomsCass covidSymptomsCass2 = new CovidSymptomsCass("2", "p2", "27-11-2022", true, true);

			patientRepository.addPatient(patient3);
			patientRepository.addPatient(patient4);

			departmentRepository.addDepartment(dept3);
			departmentRepository.addDepartment(dept4);

			doctorRepository.addDoctor(doctor3);
			doctorRepository.addDoctor(doctor4);



//			PatientCass savedPatient1 = patientRepositoryCass.save(patient1);
//			PatientCass savedPatient2 = patientRepositoryCass.save(patient2);
//			doctorRepositoryCass.save(doctor1);
//			doctorRepositoryCass.save(doctor2);
//
//			departmentRepositoryCass.save(dept1);
//			departmentRepositoryCass.save(dept2);
//
//			symptomRepositoryCass.save(covidSymptomsCass1);
//			symptomRepositoryCass.save(covidSymptomsCass2);



			patientRepositoryCass.findAll()
					.forEach(v -> log.info("Patient: {}", v.getPatientName()));
//
			patientRepositoryCass.findById(patient1.getPatientId())
					.ifPresent(v -> log.info("Patient by id: {}", v.getPatientName()));

		};
	}

}
