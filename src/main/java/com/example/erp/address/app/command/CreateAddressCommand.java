package com.example.erp.address.app.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateAddressCommand(
        @NotBlank @Size(max = 10) String cep,
        @NotBlank @Size(max = 2) String state,
        @NotBlank @Size(max = 100) String city,
        @NotBlank @Size(max = 100) String neighborhood,
        @NotBlank @Size(max = 255) String street,
        @NotBlank @Size(max = 255) String number,
        @Size(max = 255) String complement,
        @Size(max = 10) String ibgeStateCode,
        @Size(max = 10) String ibgeCityCode
) {}
