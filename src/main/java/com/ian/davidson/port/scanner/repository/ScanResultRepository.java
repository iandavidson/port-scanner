package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.ScanResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanResultRepository extends JpaRepository<ScanResult, Long> {}
