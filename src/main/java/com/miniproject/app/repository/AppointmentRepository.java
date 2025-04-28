package com.miniproject.app.repository;



import com.miniproject.app.model.Appointment;
import com.miniproject.app.model.Patient;
import com.miniproject.app.model.Doctor;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	@EntityGraph(attributePaths = {"patient", "doctor"})
    List<Appointment> findAll();

    List<Appointment> findByPatient(Patient patient);
    List<Appointment> findByDoctor(Doctor doctor);
}

