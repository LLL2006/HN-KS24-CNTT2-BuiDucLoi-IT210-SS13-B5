package com.re.ss13b5.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "prescription_details") @Data
public class PrescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medicineName;
    private Integer quantity;

    @ManyToOne @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}
