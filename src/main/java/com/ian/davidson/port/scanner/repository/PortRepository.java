package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Port;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortRepository extends JpaRepository<Port, Long> {
}
