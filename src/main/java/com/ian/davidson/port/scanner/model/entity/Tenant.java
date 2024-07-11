package com.ian.davidson.port.scanner.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Data
@AllArgsConstructor
@Builder
@Table(name = "tenant",
        uniqueConstraints =
        @UniqueConstraint(columnNames = {"name"}))
@Entity
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "tenant")
    @ToString.Exclude
    private Set<Address> addresses;

    @OneToMany(mappedBy = "tenant")
    @ToString.Exclude
    private Set<Port> ports;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
