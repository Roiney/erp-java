package com.example.erp.address.app;

import com.example.erp.address.app.command.UpdateAddressCommand;
import com.example.erp.address.app.dto.AddressDto;
import com.example.erp.address.app.mapper.AddressMapper;
import com.example.erp.address.domain.Address;
import com.example.erp.address.infra.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.validation.Valid;
import com.example.erp.address.app.command.CreateAddressCommand;

import java.util.Optional;
import java.util.UUID;

@Service
class AddressService implements AddressUseCase {
    private final AddressRepository repo;

    public AddressService(AddressRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public AddressDto get(UUID id) {
        Address a = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + id));
        return AddressMapper.toDto(a);
    }

    @Transactional
    public AddressDto ensure(@Valid CreateAddressCommand cmd) {
        Optional<Address> existing = repo.findFirstByCepAndStateAndCityAndStreetAndNumberAndNeighborhood(
                cmd.cep(), cmd.state(), cmd.city(), cmd.street(), cmd.number(), cmd.neighborhood()
        );
        Address a = existing.orElseGet(() -> repo.save(AddressMapper.toEntity(cmd)));

        if (existing.isPresent()) {
            boolean changed = false;
            if (a.getComplement() == null && cmd.complement() != null) { a.setComplement(cmd.complement()); changed = true; }
            if (a.getIbgeStateCode() == null && cmd.ibgeStateCode() != null) { a.setIbgeStateCode(cmd.ibgeStateCode()); changed = true; }
            if (a.getIbgeCityCode() == null && cmd.ibgeCityCode() != null) { a.setIbgeCityCode(cmd.ibgeCityCode()); changed = true; }
            if (changed) a = repo.save(a);
        }

        return AddressMapper.toDto(a);
    }

    @Transactional
    @Override
    public AddressDto create(@Valid CreateAddressCommand cmd) {
        Address a = repo.save(AddressMapper.toEntity(cmd));
        return AddressMapper.toDto(a);
    }

    @Transactional
    @Override
    public AddressDto update(@Valid UpdateAddressCommand cmd) {
        Address a = repo.findById(cmd.id())
                .orElseThrow(() -> new EntityNotFoundException("Address not found: " + cmd.id()));
        AddressMapper.merge(a, cmd);
        a = repo.save(a);
        return AddressMapper.toDto(a);
    }

}


