package com.queless.que.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String queueName;

    private String department;

    private String doctorName;

    private String description;

    private String status;

    private Integer maxPatients;

    private Integer currentPatients;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
