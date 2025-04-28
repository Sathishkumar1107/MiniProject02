package com.miniproject.app.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniproject.app.model.Appointment;
import com.miniproject.app.model.Doctor;
import com.miniproject.app.model.Patient;
import com.miniproject.app.service.AppointmentService;
import com.miniproject.app.service.DoctorService;
import com.miniproject.app.service.PatientService;

@Controller
@RequestMapping("/api/appointments")
public class AppointmentController {
	
	@Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;
    
    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorService.getAllDoctors()); // pass list of doctors for dropdown
        return "appointments"; // matches your appointments.html file
    }	
    
    @PostMapping
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.saveAppointment(appointment));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable Long patientId) {
        Patient patient = patientService.getPatientById(patientId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patient));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        Doctor doctor = doctorService.getDoctorById(doctorId);
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointment(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointmentById(id);
        return (appointment != null) ? ResponseEntity.ok(appointment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }
    
//    @PostMapping("/appointments")
//    public String submitAppointment(@ModelAttribute Appointment appointment, Model model) {
//        appointmentService.saveAppointment(appointment);
//        return "appointment-success"; 
//    }
//    
//    @GetMapping("/appointments")
//    public String getAllAppointments(Model model) {
//        List<Appointment> appointments = appointmentService.getAllAppointments();
//        model.addAttribute("appointments", appointments);
//        return "appointment-list"; // must match your appointment-list.html
//    }
    
   




}
