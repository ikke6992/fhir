package com.example.fhir.patient

import com.example.fhir.telecom.Telecom
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import java.time.LocalDate

@Entity
class Patient (
        @Id
        private val bsn: Long?=null,

        private var familyName: String,
        private var givenName: String,
        private var gender: String,
        private val birthDate: LocalDate,
        private var deceased: Boolean=false,
        private var address: String,

        @OneToMany(mappedBy = "patient", cascade = [CascadeType.ALL])
        private var telecoms: MutableList<Telecom> = mutableListOf()
){

    fun getFamilyName(): String {
        return familyName
    }

    fun setFamilyName(familyName: String) {
        this.familyName = familyName
    }

    fun getGivenName(): String {
        return givenName
    }

    fun setGivenName(givenName: String) {
        this.givenName = givenName
    }

    fun getGender(): String {
        return gender
    }

    fun setGender(gender: String) {
        this.gender = gender
    }

    fun getBirthDate(): LocalDate {
        return birthDate
    }

    fun getAddress(): String {
        return address
    }

    fun setAddress(address: String) {
        this.address = address
    }

    fun getDeceased(): Boolean {
        return deceased
    }

    fun setDeceased(deceased: Boolean) {
        this.deceased = deceased
    }

    fun getBsn(): Long? {
        return bsn
    }

    fun getTelecoms(): List<Telecom> {
        return telecoms
    }

    fun setTelecoms(telecoms: MutableList<Telecom>) {
        this.telecoms = telecoms
    }

    fun addTelecom(telecom: Telecom) {
        telecoms.add(telecom)
    }

}