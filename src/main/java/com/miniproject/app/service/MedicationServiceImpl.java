package com.miniproject.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.app.model.Medication;
import com.miniproject.app.model.Patient;
import com.miniproject.app.repository.MedicationRepository;

@Service
public class MedicationServiceImpl implements MedicationService{
	
	
	@Autowired
    private MedicationRepository medicationRepository;
	
	
	@Override
	public Medication saveMedication(Medication medication) {
		// TODO Auto-generated method stub
		return medicationRepository.save(medication);
	}

	@Override
	public List<Medication> getMedicationsByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return medicationRepository.findByPatient(patient);
	}

	@Override
	public Medication getMedicationById(Long id) {
		// TODO Auto-generated method stub
		 return medicationRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteMedication(Long id) {
		// TODO Auto-generated method stub
		medicationRepository.deleteById(id);
		
	}

	@Override
	public List<Medication> getAllMedications() {
	    return medicationRepository.findAll();
	}

}
