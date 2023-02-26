package com.example.CoronaApi.repository;

import com.example.CoronaApi.Cassandra.dataClasses.DoctorCass;
import com.example.CoronaApi.Cassandra.repositories.DoctorRepositoryCass;
import com.example.CoronaApi.model.request.DoctorRequst;
import com.example.CoronaApi.model.response.Doctor;
import com.example.CoronaApi.model.GeneralResponse;
import com.example.CoronaApi.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorRepository {

    @Autowired
    public DoctorRepositoryCass doctorRepositoryCass;

    @Autowired
    private ObjectConverter objectConverter;

    public Collection<Doctor> getDoctorList() {
        List<DoctorCass> doctorCasses = (List<DoctorCass>) doctorRepositoryCass.findAll();
        return doctorCasses.stream()
                .map(doctorCass -> objectConverter.from(objectConverter.toJson(doctorCass), Doctor.class))
                .collect(Collectors.toList());
    }

    public Doctor getDoctorById(String doctorId) {
        DoctorCass doctorCass = doctorRepositoryCass.findById(doctorId).orElse(null);
        return doctorCass != null ? objectConverter.from(objectConverter.toJson(doctorCass), Doctor.class) : null;
    }

    public GeneralResponse deleteDoctorById(String doctorId) {
        doctorRepositoryCass.deleteById(doctorId);
        GeneralResponse generalResponse = new GeneralResponse();
        generalResponse.setId(doctorId);
        generalResponse.setResult("Success");
        return generalResponse;
    }


    public GeneralResponse addDoctor(DoctorRequst doctor) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            DoctorCass doctorCass = new DoctorCass();
            doctorCass.setDoctorId("dc" + System.currentTimeMillis());
            doctorCass.setDepartmentId(doctor.getDepartmentId());
            doctorCass.setDoctorName(doctor.getDoctorName());
            doctorRepositoryCass.save(doctorCass);
            generalResponse.setId(doctorCass.getDoctorId());
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }

    public GeneralResponse updateDoctor(DoctorRequst doctor) {
        GeneralResponse generalResponse = new GeneralResponse();
        try {
            DoctorCass doctorCass = new DoctorCass();
            doctorCass.setDoctorId(doctor.getDoctorId());
            doctorCass.setDepartmentId(doctor.getDepartmentId());
            doctorCass.setDoctorName(doctor.getDoctorName());
            doctorRepositoryCass.save(doctorCass);
            generalResponse.setId(doctorCass.getDoctorId());
            generalResponse.setResult("Success");
        } catch (Exception e) {
            System.out.println("Failure " + e.getMessage());
            generalResponse.setId(String.valueOf(-1));
            generalResponse.setResult("Failure");
        }
        return generalResponse;
    }
}
