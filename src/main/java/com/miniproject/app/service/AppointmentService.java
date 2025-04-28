package com.miniproject.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miniproject.app.model.Appointment;
import com.miniproject.app.model.Doctor;
import com.miniproject.app.model.Patient;


public interface AppointmentService {
	
	Appointment saveAppointment(Appointment appointment);
    List<Appointment> getAppointmentsByPatient(Patient patient);
    List<Appointment> getAppointmentsByDoctor(Doctor doctor);
    Appointment getAppointmentById(Long id);
    void deleteAppointment(Long id);
    List<Appointment> getAllAppointments();

}
