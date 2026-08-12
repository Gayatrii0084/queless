package com.queless.que.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.queless.que.Entity.PatientEntity;
import com.queless.que.Entity.PatientStatus;
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

        // Generate Token
        Integer maxToken = repository.findMaxTokenNumberByQueueId(queueId);

        if (maxToken == null) {
            patient.setTokenNumber(1);
        } else {
            patient.setTokenNumber(maxToken + 1);
        }

        patient.setStatus(PatientStatus.WAITING);
        patient.setCheckInTime(LocalDateTime.now());
        patient.setCompletedTime(null);
        patient.setQueue(queue);

        return repository.save(patient);
    }

    // Call Next Patient
    public PatientEntity callNextPatient(Integer queueId) {

        // Check if someone is already in consultation
        Optional<PatientEntity> consultationPatient =
                repository.findByQueueIdAndStatus(
                        queueId,
                        PatientStatus.IN_CONSULTATION);

        if (consultationPatient.isPresent()) {
            return null;
        }

        // Find the first waiting patient
        Optional<PatientEntity> waitingPatient =
                repository.findFirstByQueueIdAndStatusOrderByTokenNumberAsc(
                        queueId,
                        PatientStatus.WAITING);

        if (waitingPatient.isEmpty()) {
            return null;
        }

        PatientEntity patient = waitingPatient.get();

        patient.setStatus(PatientStatus.IN_CONSULTATION);

        return repository.save(patient);
    }

    // Complete Consultation
    public PatientEntity completeConsultation(Integer patientId) {

        Optional<PatientEntity> optionalPatient =
                repository.findById(patientId);

        if (optionalPatient.isEmpty()) {
            return null;
        }

        PatientEntity patient = optionalPatient.get();

        if (patient.getStatus() != PatientStatus.IN_CONSULTATION) {
            return null;
        }

        patient.setStatus(PatientStatus.COMPLETED);

        patient.setCompletedTime(LocalDateTime.now());

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