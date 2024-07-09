package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
