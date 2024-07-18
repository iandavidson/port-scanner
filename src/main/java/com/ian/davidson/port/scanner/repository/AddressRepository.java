package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Address;
import com.ian.davidson.port.scanner.model.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Modifying
    @Query(value = "delete from address where tenant_id = (:tenantId) ", nativeQuery = true)
    void deleteAllByTenantId(@Param("tenantId") Long tenantId);

    void deleteByTenant(Tenant tenant);
}
