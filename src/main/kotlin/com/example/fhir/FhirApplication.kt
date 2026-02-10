package com.example.fhir

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FhirApplication

fun main(args: Array<String>) {
	runApplication<FhirApplication>(*args)
}
