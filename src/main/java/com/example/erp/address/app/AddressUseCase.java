package com.example.erp.address.app;

import com.example.erp.address.app.command.CreateAddressCommand;
import com.example.erp.address.app.command.UpdateAddressCommand;
import com.example.erp.address.app.dto.AddressDto;

import java.util.UUID;

public interface AddressUseCase {
    AddressDto get(UUID id);
    AddressDto ensure(CreateAddressCommand cmd);
    AddressDto create(CreateAddressCommand cmd);
    AddressDto update(UpdateAddressCommand cmd);
}
