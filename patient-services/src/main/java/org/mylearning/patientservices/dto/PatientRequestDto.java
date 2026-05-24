package org.mylearning.patientservices.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record PatientRequestDto(
        @NotBlank(message = "Name cannot be blank")
        String name,

        @Email(message = "Email not in correct format")
        String email,

        @NotBlank(message = "Address is required")
        String address,
        String dateOfBirth
) {
}
