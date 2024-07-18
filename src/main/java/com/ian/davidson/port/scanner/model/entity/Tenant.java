package com.ian.davidson.port.scanner.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "tenant", fetch = FetchType.EAGER)
    @ToString.Exclude
//    @Builder.Default
//    private Set<Address> addresses = new HashSet<>();
    private Set<Address> addresses;

    @OneToMany(mappedBy = "tenant", fetch = FetchType.EAGER)
    @ToString.Exclude
//    @Builder.Default
//    private Set<Port> ports = new HashSet<>();
    private Set<Port> ports;

    @CreationTimestamp
    @Column(name = "creation_date")
    private LocalDateTime creationDate;

}
