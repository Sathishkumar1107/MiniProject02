package com.miniproject.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.app.model.Doctor;
import com.miniproject.app.service.DoctorService;

@RestController
@RequestMapping("/api/doctors")

public class DoctorController {
	
	 @Autowired
	    private DoctorService doctorService;
	 
	 
	 @PostMapping
	    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
	        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
	    }
	 
	 
	 @GetMapping
	    public List<Doctor> getAllDoctors() {
	        return doctorService.getAllDoctors();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Doctor> getDoctor(@PathVariable Long id) {
	        Doctor doctor = doctorService.getDoctorById(id);
	        return (doctor != null) ? ResponseEntity.ok(doctor) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
	        doctorService.deleteDoctor(id);
	        return ResponseEntity.ok().build();
	    }

	
	
}
