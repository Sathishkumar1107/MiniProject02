package com.miniproject.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniproject.app.model.Appointment;
import com.miniproject.app.model.Doctor;
import com.miniproject.app.model.Patient;
import com.miniproject.app.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService{
	
	@Autowired
    private AppointmentRepository appointmentRepository;

	@Override
	public Appointment saveAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return appointmentRepository.save(appointment);
	}

	@Override
	public List<Appointment> getAppointmentsByPatient(Patient patient) {
		// TODO Auto-generated method stub
		return appointmentRepository.findByPatient(patient);
	}

	@Override
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		 return appointmentRepository.findByDoctor(doctor);
	}

	@Override
	public Appointment getAppointmentById(Long id) {
		// TODO Auto-generated method stub
		return appointmentRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteAppointment(Long id) {
		// TODO Auto-generated method stub
		 appointmentRepository.deleteById(id);
		
	}
	
	@Override
	public List<Appointment> getAllAppointments() {
	    return appointmentRepository.findAll();
	}

	


}
