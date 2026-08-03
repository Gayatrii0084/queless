package com.queless.que.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queless.que.Entity.PatientEntity;
import com.queless.que.Entity.QueueEntity;
import com.queless.que.Repository.PatientRepository;
import com.queless.que.Repository.QueueRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository repository;

    @Autowired
    private QueueRepository queueRepository;

    // Save Patient in Queue
    public PatientEntity savePatient(Integer queueId, PatientEntity patient) {

        QueueEntity queue = queueRepository.findById(queueId).orElse(null);

        if (queue == null) {
            return null;
        }

        patient.setQueue(queue);

        return repository.save(patient);
    }

    // Get All Patients
    public List<PatientEntity> getAllPatients() {
        return repository.findAll();
    }

    // Get Patient By Id
    public PatientEntity getPatientById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    // Update Patient
    public PatientEntity updatePatient(PatientEntity patient) {
        return repository.save(patient);
    }

    // Delete Patient
    public void deletePatient(Integer id) {
        repository.deleteById(id);
    }
}