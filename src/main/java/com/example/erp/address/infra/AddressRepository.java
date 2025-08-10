package com.example.erp.address.infra;

import com.example.erp.address.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    Optional<Address> findFirstByCepAndStateAndCityAndStreetAndNumberAndNeighborhood(
            String cep, String state, String city, String street, String number, String neighborhood
    );

    List<Address> findByCep(String cep);
}
