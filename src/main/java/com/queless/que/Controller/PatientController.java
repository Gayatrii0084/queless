package com.queless.que.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.queless.que.Entity.PatientEntity;
import com.queless.que.Service.PatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService service;

    // Test API
    @GetMapping
    public String testApi() {
        return "Patient API is working";
    }

    // Save Patient in Queue
    @PostMapping("/queue/{queueId}")
    public PatientEntity savePatient(@PathVariable Integer queueId,
                                     @RequestBody PatientEntity patient) {

        return service.savePatient(queueId, patient);
    }

    // Get All Patients
    @GetMapping("/getAll")
    public List<PatientEntity> getAllPatients() {
        return service.getAllPatients();
    }

    // Get Patient By ID
    @GetMapping("/getById/{id}")
    public PatientEntity getPatientById(@PathVariable Integer id) {
        return service.getPatientById(id);
    }

    // Update Patient
    @PutMapping("/update")
    public PatientEntity updatePatient(@RequestBody PatientEntity patient) {
        return service.updatePatient(patient);
    }

    // Delete Patient
    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable Integer id) {
        service.deletePatient(id);
        return "Patient Deleted Successfully";
    }
}