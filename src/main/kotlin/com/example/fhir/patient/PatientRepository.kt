package com.example.fhir.patient

import org.springframework.data.jpa.repository.JpaRepository

interface PatientRepository: JpaRepository<Patient, Long> {
}