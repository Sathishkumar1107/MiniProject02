package com.miniproject.app.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.miniproject.app.model.Appointment;
import com.miniproject.app.model.Doctor;
import com.miniproject.app.model.Patient;
import com.miniproject.app.service.AppointmentService;
import com.miniproject.app.service.DoctorService;
import com.miniproject.app.service.PatientService;

@Controller
public class AppointmentViewController {
	
	@Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;
    
    @GetMapping("/appointments/list")
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentService.getAllAppointments());
        return "appointment-list";
    }
    
    @GetMapping("/appointments/success")
    public String appointmentSuccess(@RequestParam Long id, Model model) {
        System.out.println(">>> Loading success page for appointment ID: " + id);
        Appointment appointment = appointmentService.getAppointmentById(id);
        model.addAttribute("appointment", appointment);
        return "appointment-success"; // HTML file: appointment-success.html
    }

    @GetMapping("/appointments")
    public String showAppointmentForm(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        model.addAttribute("patients", patientService.getAllPatients());
        return "appointments";
    }
    
    @GetMapping("/appointment")
    public String showAppointmentPage() {
        return "appointments"; // no .html
    }
    

    @PostMapping("/appointments")
    public String handleAppointmentForm(
            @RequestParam Long patientId,
            @RequestParam Long doctorId,
            @RequestParam String appointmentDate,
            Model model
    ) {
    	
    	System.out.println(">>> Appointment form submitted successfully");
    	
    	Patient patient = patientService.getPatientById(patientId);
        Doctor doctor = doctorService.getDoctorById(doctorId);
        
        if (patient == null || doctor == null) {
            model.addAttribute("error", "Invalid Patient or Doctor ID.");
            return "appointments";
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime date = LocalDateTime.parse(appointmentDate, formatter);

            Appointment appointment = new Appointment();
            appointment.setPatient(patient);
            appointment.setDoctor(doctor);
            appointment.setAppointmentDate(date);
            appointment.setStatus("Scheduled");
            
            System.out.println(">>> Appointment object before save: " + appointment);

            Appointment saved = appointmentService.saveAppointment(appointment);

            System.out.println(">>> Appointment saved with ID: " + saved.getId());

            return "redirect:/appointments/success?id=" + saved.getId();

        } catch (Exception e) {
        	System.out.println(">>> Error during booking: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "Something went wrong while booking. " + e.getMessage());
            return "appointments";
        }
        
        
        
    }
    
	
}
