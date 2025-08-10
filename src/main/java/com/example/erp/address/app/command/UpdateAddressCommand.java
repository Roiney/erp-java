package com.example.erp.address.app.command;

import jakarta.validation.constraints.Size;
import java.util.UUID;

public record UpdateAddressCommand(
        UUID id,
        @Size(max = 10) String cep,
        @Size(max = 2) String state,
        @Size(max = 100) String city,
        @Size(max = 100) String neighborhood,
        @Size(max = 255) String street,
        @Size(max = 255) String number,
        @Size(max = 255) String complement,
        @Size(max = 10) String ibgeStateCode,
        @Size(max = 10) String ibgeCityCode
) {}
