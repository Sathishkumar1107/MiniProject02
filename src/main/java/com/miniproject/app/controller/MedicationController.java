package com.miniproject.app.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.app.model.Medication;
import com.miniproject.app.model.Patient;
import com.miniproject.app.service.MedicationService;
import com.miniproject.app.service.PatientService;

@RestController
@RequestMapping("/api/medications")

public class MedicationController {
	
	@Autowired
    private MedicationService medicationService;

    @Autowired
    private PatientService patientService;
    
    @PostMapping
    public ResponseEntity<Medication> addMedication(@RequestBody Medication medication) {
        return ResponseEntity.ok(medicationService.saveMedication(medication));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Medication>> getMedicationsByPatient(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(medicationService.getMedicationsByPatient(patient));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medication> getMedication(@PathVariable Long id) {
        Medication medication = medicationService.getMedicationById(id);
        return (medication != null) ? ResponseEntity.ok(medication) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedication(id);
        return ResponseEntity.ok().build();
    }

}
