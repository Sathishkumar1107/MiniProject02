package com.miniproject.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.app.model.Patient;
import com.miniproject.app.repository.PatientRepository;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
    private PatientRepository patientRepository;

	@Override
	public Patient savePatient(Patient patient) {
		// TODO Auto-generated method stub
		return patientRepository.save(patient);
	}

	@Override
	public List<Patient> getAllPatients() {
		// TODO Auto-generated method stub
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientById(Long id) {
		// TODO Auto-generated method stub
		return patientRepository.findById(id).orElse(null);
	}

	@Override
	public void deletePatient(Long id) {
		// TODO Auto-generated method stub
		 patientRepository.deleteById(id);
	}

}
