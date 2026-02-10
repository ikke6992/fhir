package com.example.fhir.patient

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

@JvmRecord
data class PatientRequest(val identifier: List<Identifier>, val name: List<Name>, val telecom: List<TelecomRequest>, val gender: String,
                          val birthDate: String, val deceasedBoolean: Boolean, val address: List<Address>)

@JvmRecord
data class Identifier(val system: String, val value: String)

@JvmRecord
data class Name(val family: String, val given: List<String>)

@JvmRecord
data class TelecomRequest(val system: String, val value: String)

@JvmRecord
data class Address(
        @JsonProperty("_line")
        val line: List<LineExtension>,
        val city: String,
        val postalCode: String)

@JvmRecord
data class LineExtension(val extension: List<AddressExtension>)

@JvmRecord
data class AddressExtension(val url: String, val valueString: String)

