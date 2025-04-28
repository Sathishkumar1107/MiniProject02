package com.miniproject.app.service;

import java.util.List;

import com.miniproject.app.model.Medication;
import com.miniproject.app.model.Patient;

public interface MedicationService {
	
	 Medication saveMedication(Medication medication);
	    List<Medication> getMedicationsByPatient(Patient patient);
	    Medication getMedicationById(Long id);
	    void deleteMedication(Long id);
	    List<Medication> getAllMedications(); 

}
