package com.ian.davidson.port.scanner.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@Builder
@Table(name = "scan_result")
@Entity
public class ScanResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    private final String ip;
    private final Integer port;

    @Column(name = "time_out")
    private final Integer timeOut;
    private final Boolean exposed;
    private final ConnectionStatus status;

    @ManyToOne
    private final Tenant tenant;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
