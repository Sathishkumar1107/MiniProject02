package com.miniproject.app.repository;


import com.miniproject.app.model.Medication;
import com.miniproject.app.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findByPatient(Patient patient);
}

