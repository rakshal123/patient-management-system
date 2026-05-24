package org.mylearning.patientservices.mapper;

import org.mylearning.patientservices.dto.PatientRequestDto;
import org.mylearning.patientservices.dto.PatientResponseDto;
import org.mylearning.patientservices.entity.Patient;

import java.time.LocalDate;
import java.time.ZoneId;

public class PatientMapper {

    public static PatientResponseDto toPatientResponseDto(Patient patient){
        return new PatientResponseDto(
                patient.getName(),
                patient.getEmail(),
                patient.getAddress(),
                patient.getDateOfBirth(),
                patient.getRegisteredDate()
        );
    }

    public static Patient toPatient(PatientRequestDto patientRequestDto){
        Patient patient = new Patient();
        patient.setName(patientRequestDto.name());
        patient.setEmail(patientRequestDto.email());
        patient.setAddress(patientRequestDto.address());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.dateOfBirth()));
        patient.setRegisteredDate(LocalDate.now(ZoneId.of("Asia/Kolkata")));
        return patient;
    }
}
