package org.mylearning.patientservices.service;

import org.mylearning.patientservices.dto.PatientRequestDto;
import org.mylearning.patientservices.dto.PatientResponseDto;
import org.mylearning.patientservices.entity.Patient;
import org.mylearning.patientservices.exceptions.EmailAlreadyExistsException;
import org.mylearning.patientservices.mapper.PatientMapper;
import org.mylearning.patientservices.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDto> getAllPatients(){
        List<Patient> allPatients = patientRepository.findAll();
        return allPatients.stream()
                .map(PatientMapper::toPatientResponseDto)
                .toList();
    }

    public PatientResponseDto createPatient(PatientRequestDto patientRequestDto){
        if(patientRepository.existsByEmail(patientRequestDto.email())) {
            throw new EmailAlreadyExistsException("The email passed is already present");
        }

        Patient patient = patientRepository.save(PatientMapper.toPatient(patientRequestDto));
        return PatientMapper.toPatientResponseDto(patient);
    }

    public PatientResponseDto updatePatient(UUID id, PatientRequestDto patientRequestDto){
        Patient patient = patientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Patient Not Found")
        );

        if(patientRepository.existsByEmailAndIdNot(patientRequestDto.email(), id)){
            throw new RuntimeException("Patient Email Already Exists");
        }

        patient.setName(patientRequestDto.name());
        patient.setAddress(patientRequestDto.address());
        patient.setEmail(patientRequestDto.email());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDto.dateOfBirth()));
        Patient updatedPatient = patientRepository.save(patient);

        return PatientMapper.toPatientResponseDto(updatedPatient);
    }
    public void deletePatient(UUID id){
        patientRepository.deleteById(id);
    }
}
