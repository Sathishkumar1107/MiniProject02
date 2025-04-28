package com.miniproject.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miniproject.app.model.Medication;
import com.miniproject.app.model.Patient;
import com.miniproject.app.service.MedicationService;
import com.miniproject.app.service.PatientService;

@Controller
public class MedicationViewController {
	
	@Autowired
    private MedicationService medicationService;

    @Autowired
    private PatientService patientService;

    @GetMapping("/medications")
    public String showMedicationsPage(Model model) {
        model.addAttribute("medications", medicationService.getAllMedications()); 
        return "medications";
    }
    
    @PostMapping("/medications")
    public String handleMedicationForm(
        @RequestParam Long patientId,
        @RequestParam String name,
        @RequestParam String dosage,
        Model model
    ) {
        Medication med = new Medication();
        med.setName(name);
        med.setDosage(dosage);

        Patient patient = patientService.getPatientById(patientId);

        if (patient != null) {
            med.setPatient(patient);
            medicationService.saveMedication(med);
        } else {
            model.addAttribute("error", "Invalid Patient ID");
        }
        
        model.addAttribute("medications", medicationService.getAllMedications());
        return "medications";
    }

	
}
