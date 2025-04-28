package com.miniproject.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.app.model.Doctor;
import com.miniproject.app.repository.DoctorRepository;


@Service
public class DoctorServiceImpl implements DoctorService {
	
	
	@Autowired
    private DoctorRepository doctorRepository;
	
	@Override
	public Doctor saveDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		 return doctorRepository.save(doctor);
	}

	@Override
	public List<Doctor> getAllDoctors() {
		// TODO Auto-generated method stub
		return doctorRepository.findAll();
	}

	@Override
	public Doctor getDoctorById(Long id) {
		// TODO Auto-generated method stub
		return doctorRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteDoctor(Long id) {
		// TODO Auto-generated method stub
		doctorRepository.deleteById(id);
	}
	
	

   
}
