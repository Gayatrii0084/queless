package com.queless.que.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String patientName;

    private Integer age;

    private String gender;

    private String phone;

    private Integer tokenNumber;

    @Enumerated(EnumType.STRING)
    private PatientStatus status;

    private LocalDateTime checkInTime;

    private LocalDateTime completedTime;

    @ManyToOne
    @JoinColumn(name = "queue_id")
    private QueueEntity queue;
}