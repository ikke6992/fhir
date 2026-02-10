package com.example.fhir.telecom

import org.springframework.data.jpa.repository.JpaRepository

interface TelecomRepository : JpaRepository<Telecom, Long> {
}