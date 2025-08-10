package com.example.erp.address.app.mapper;

import com.example.erp.address.app.command.CreateAddressCommand;
import com.example.erp.address.app.command.UpdateAddressCommand;
import com.example.erp.address.app.dto.AddressDto;
import com.example.erp.address.domain.Address;

import java.util.UUID;

public final class AddressMapper {
    private AddressMapper() {}

    public static Address toEntity(CreateAddressCommand c) {
        return new Address(
                UUID.randomUUID(),
                c.cep(),
                c.state(),
                c.city(),
                c.neighborhood(),
                c.street(),
                c.number(),
                c.complement(),
                c.ibgeStateCode(),
                c.ibgeCityCode()
        );
    }

    public static void merge(Address a, UpdateAddressCommand c) {
        if (c.cep() != null) a.setCep(c.cep());
        if (c.state() != null) a.setState(c.state());
        if (c.city() != null) a.setCity(c.city());
        if (c.neighborhood() != null) a.setNeighborhood(c.neighborhood());
        if (c.street() != null) a.setStreet(c.street());
        if (c.number() != null) a.setNumber(c.number());
        if (c.complement() != null) a.setComplement(c.complement());
        if (c.ibgeStateCode() != null) a.setIbgeStateCode(c.ibgeStateCode());
        if (c.ibgeCityCode() != null) a.setIbgeCityCode(c.ibgeCityCode());
    }

    public static AddressDto toDto(Address a) {
        return new AddressDto(
                a.getId(), a.getCep(), a.getState(), a.getCity(), a.getNeighborhood(),
                a.getStreet(), a.getNumber(), a.getComplement(), a.getIbgeStateCode(), a.getIbgeCityCode()
        );
    }
}
