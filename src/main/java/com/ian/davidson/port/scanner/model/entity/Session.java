package com.ian.davidson.port.scanner.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@Builder
@Entity(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne
    private final Tenant tenant;

    @CreationTimestamp
    @Column(name = "creation_date")
    private final LocalDateTime creationDate;
}
