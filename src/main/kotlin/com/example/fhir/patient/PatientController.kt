package com.example.fhir.patient

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.NumberFormatException
import java.time.LocalDate
import java.time.format.DateTimeParseException

@RestController
@CrossOrigin
@RequestMapping
class PatientController(
        private var patientService: PatientService
) {

    @GetMapping("patients")
    fun getAll() :  List<Patient> {
        return patientService.getAll()
    }

    @GetMapping("patients/{bsn}")
    fun get(@PathVariable bsn: Long) : ResponseEntity<*> {

        /*  Check whether a patient with this bsn exists in database.
            Return Bad Request if this check fails and OK if this check succeeds.
         */
        var patient = patientService.get(bsn)
        if (patient.isEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("patient with bsn $bsn does not exist in this system")
        }
        return ResponseEntity.ok(PatientResponse.toResponse(patient.get()))
    }

    @PostMapping("fhir/patient")
    fun postPatient(@RequestBody json: PatientRequest) : ResponseEntity<*> {

        /*  Check if the PatientRequest contains a BSN and whether this BSN is not yet in use.
            Return Bad Request to user if these checks fail.
        */
        var bsn: Long = 0
        for (i in 0..<json.identifier.size) {
            if (json.identifier[i].system == "http://fhir.nl/fhir/NamingSystem/bsn") {
                try {
                    bsn = json.identifier[i].value.toLong()
                } catch (e: NumberFormatException) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("the bsn cannot be parsed to a long to be used as identifier")
                }
                break
            }
        }
        if (bsn == 0L) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("identifier needs to adhere to the http://fhir.nl/fhir/NamingSystem/bsn system")
        }
        if (!patientService.get(bsn).isEmpty) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("patient with bsn $bsn already exists in this system")
        }

        /*  Check whether the PatientRequest contains at least one family name and at least one given name.
            Return Bad Request to the user if these checks fail.
         */
        var name: Name = json.name[0]
        if (name.family.equals(null) || name.family.equals("")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("patient requires family name")
        }
        var familyName: String = name.family
        if (name.given.size == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("patient requires at least one given name")
        }
        var givenName: String = name.given[0]
        for (i in 1..<name.given.size) {
            givenName += " " + name.given[i]
        }

        /*  Check whether the date is a valid date.
            Return Bad Request to the user if these checks fail.
         */
        var date : LocalDate
        try {
            date = LocalDate.parse(json.birthDate)
        } catch (e: DateTimeParseException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("birthDate is not a correct date")
        }
        if (date.isAfter(LocalDate.now())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("birthDate must be before current date")
        }

        // Create address as single string
        var address: String = json.address[0].line[0].extension[0].valueString + " " +
                json.address[0].line[0].extension[1].valueString + " " +
                json.address[0].postalCode + " " +
                json.address[0].city

        // Create patient from data in PatientRequest and save to database. Return OK to user.
        var patient = patientService.postPatient(bsn, familyName, givenName, json.telecom,
                json.gender, date, json.deceasedBoolean, address)
        return ResponseEntity.ok(PatientResponse.toResponse(patient))
    }
}