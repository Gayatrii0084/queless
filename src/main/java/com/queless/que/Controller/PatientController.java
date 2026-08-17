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

    // Save Patient
    @PostMapping("/queue/{queueId}")
    public PatientEntity savePatient(@PathVariable Integer queueId,
                                     @RequestBody PatientEntity patient) {

        return service.savePatient(queueId, patient);
    }

    // Call Next Patient
    @PutMapping("/queue/{queueId}/call-next")
    public PatientEntity callNextPatient(@PathVariable Integer queueId) {

        return service.callNextPatient(queueId);
    }
 // Get Current Patient
    @GetMapping("/queue/{queueId}/current")
    public PatientEntity getCurrentPatient(@PathVariable Integer queueId) {

        return service.getCurrentPatient(queueId);
    }
 // Get Next Waiting Patient
    @GetMapping("/queue/{queueId}/next")
    public PatientEntity getNextWaitingPatient(@PathVariable Integer queueId) {

        return service.getNextWaitingPatient(queueId);
    }
 // Get Waiting Patient Count
    @GetMapping("/queue/{queueId}/waiting-count")
    public long getWaitingPatientCount(@PathVariable Integer queueId) {

        return service.getWaitingPatientCount(queueId);
    }

    // Get Completed Patient Count
    @GetMapping("/queue/{queueId}/completed-count")
    public long getCompletedPatientCount(@PathVariable Integer queueId) {

        return service.getCompletedPatientCount(queueId);
    }

    // Get Skipped Patient Count
    @GetMapping("/queue/{queueId}/skipped-count")
    public long getSkippedPatientCount(@PathVariable Integer queueId) {

        return service.getSkippedPatientCount(queueId);
    }

    // Get Total Patient Count
    @GetMapping("/queue/{queueId}/total-count")
    public long getTotalPatientCount(@PathVariable Integer queueId) {

        return service.getTotalPatientCount(queueId);
    }
 // Complete Consultation
    @PutMapping("/complete/{patientId}")
    public PatientEntity completeConsultation(@PathVariable Integer patientId) {

        return service.completeConsultation(patientId);
    }
 // Skip Patient
    @PutMapping("/skip/{patientId}")
    public PatientEntity skipPatient(@PathVariable Integer patientId) {

        return service.skipPatient(patientId);
    }
    // Get All Patients
    @GetMapping("/getAll")
    public List<PatientEntity> getAllPatients() {
        return service.getAllPatients();
    }

    // Get Patient By Id
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