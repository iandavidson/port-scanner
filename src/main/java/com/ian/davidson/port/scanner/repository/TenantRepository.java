package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TenantRepository  extends JpaRepository<Tenant, Long> {

    Optional<Tenant> findByName(String name);
}
