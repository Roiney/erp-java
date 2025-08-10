package com.example.erp.address.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "address", schema = "public")
public class Address {

    @Id
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "cep", nullable = false, length = 10)
    private String cep;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "city", nullable = false, length =100)
    private String city;

    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @Column(name = "street", nullable = false, length = 255)
    private String street;

    @Column(name = "number", nullable = false, length = 255)
    private String number;

    @Column(name = "complement", length = 255)
    private String complement;

    @Column(name = "ibge_state_code", length = 10)
    private String ibgeStateCode;

    @Column(name = "ibge_city_code", length = 10)
    private String ibgeCityCode;

    protected Address(){}

    public Address(UUID id, String cep, String state, String city, String neighborhood,
                   String street, String number, String complement,
                   String ibgeStateCode, String ibgeCityCode) {
        this.id = id;
        this.cep = cep;
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.ibgeStateCode = ibgeStateCode;
        this.ibgeCityCode = ibgeCityCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getIbgeStateCode() {
        return ibgeStateCode;
    }

    public void setIbgeStateCode(String ibgeStateCode) {
        this.ibgeStateCode = ibgeStateCode;
    }

    public String getIbgeCityCode() {
        return ibgeCityCode;
    }

    public void setIbgeCityCode(String ibgeCityCode) {
        this.ibgeCityCode = ibgeCityCode;
    }
}
