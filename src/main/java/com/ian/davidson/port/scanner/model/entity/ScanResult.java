package com.ian.davidson.port.scanner.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "scan_result")
@Entity
@ToString
public class ScanResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String address;

    @Column(nullable = false)
    @NotNull
    private Integer port;

    @Column(name = "time_out")
    @NotNull
    private Integer timeOut;

    @Column(nullable = false)
    @NotNull
    private ConnectionStatus status;

    @Column(name = "session_id")
    @NotNull
    private Long sessionId;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
