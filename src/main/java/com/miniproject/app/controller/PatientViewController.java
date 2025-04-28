package com.miniproject.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.miniproject.app.model.Patient;
import com.miniproject.app.service.PatientService;


@Controller
public class PatientViewController {
		
	@Autowired
    private PatientService patientService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "register"; // maps to register.html
    }

    @PostMapping("/register")
    public String handleFormSubmit(@ModelAttribute("patient") Patient patient) {
        patientService.savePatient(patient);
        return "redirect:/success"; // Or redirect to a profile page
    }

    @GetMapping("/success")
    public String successPage() {
        return "success"; // Create success.html
    }
    
    @GetMapping("/patients/{id}")
    public String viewPatientProfile(@PathVariable Long id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
        	model.addAttribute("errorMessage", "Patient with ID " + id + " not found.");
            return "error"; // Optional: create an error.html page
        }
        model.addAttribute("patient", patient);
        return "profile"; // maps to profile.html
    }
    
    @GetMapping("/patients/list")
    public String listPatients(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        return "patients-list"; // maps to patients-list.html
    }


	
	
}
