package com.example.fhir.patient

import com.example.fhir.telecom.Telecom
import com.example.fhir.telecom.TelecomRepository

import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.Optional

@Service
class PatientService(
        private val patientRepository: PatientRepository,
        private val telecomRepository: TelecomRepository
) {

    fun getAll() : List<Patient> {
        return patientRepository.findAll()
    }

    fun get(bsn: Long): Optional<Patient> {
        return patientRepository.findById(bsn)
    }

    fun postPatient(bsn: Long, familyName: String, givenName: String,
                    telecom: List<TelecomRequest>, gender: String, birthDate: LocalDate,
                    deceasedBoolean: Boolean, address: String) : Patient {
        var patient = Patient(bsn, familyName, givenName, gender, birthDate, deceasedBoolean, address)
        patientRepository.save(patient)
        for (telecomRequest in telecom) {

            var com: Telecom = Telecom(telecomRequest.system, telecomRequest.value, patient)
            telecomRepository.save(com)
            patient.addTelecom(com)
        }
        return patientRepository.save(patient)
    }
}