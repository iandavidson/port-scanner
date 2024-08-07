package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Address;
import java.util.Set;
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

    @Query(value = "select * from address where tenant_id = (:tenantId) order by address asc", nativeQuery = true)
    Set<Address> findAllByTenantId(@Param("tenantId") Long tenantId);
}
