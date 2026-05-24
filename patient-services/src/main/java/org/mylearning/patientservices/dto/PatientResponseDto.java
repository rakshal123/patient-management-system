package org.mylearning.patientservices.dto;

import java.time.LocalDate;

public record PatientResponseDto(
        String name,
        String email,
        String address,
        LocalDate birthDate,
        LocalDate registeredDate
) {
}
