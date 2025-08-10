package com.example.erp.address.app.dto;

import java.util.UUID;

public record AddressDto(
        UUID id,
        String cep,
        String state,
        String city,
        String neighborhood,
        String street,
        String number,
        String complement,
        String ibgeStateCode,
        String ibgeCityCode
) {
}
