package com.example.fhir.patient

import java.time.LocalDate

@JvmRecord
data class PatientResponse(
        val bsn : Long?,
        val name: String,
        val gender: String,
        val birthDate: LocalDate,
        val deceased: Boolean,
        val address: String,
        val telecom: List<String>,
) {
    companion object {

        fun toResponse(patient: Patient) : PatientResponse {

            val bsn = patient.getBsn()
            val name = patient.getGivenName() + " " + patient.getFamilyName()
            val gender = patient.getGender()
            val birthDate = patient.getBirthDate()
            val deceased = patient.getDeceased()
            val address = patient.getAddress()
            val telecom = patient.getTelecoms().map {
                "${it.getSystem()}: ${it.getValue()}"
            }

            return PatientResponse(bsn, name, gender, birthDate, deceased, address, telecom)

    }
}
}