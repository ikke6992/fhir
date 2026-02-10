package com.example.fhir.telecom

import com.example.fhir.patient.Patient
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class Telecom(
        private val system: String,
        private var value: String,

        @ManyToOne
        private var patient: Patient,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long?=null,
) {

        fun getSystem() : String {
                return system
        }

        fun getValue() : String {
                return value
        }

        fun setValue(value : String) {
                this.value = value
        }

}