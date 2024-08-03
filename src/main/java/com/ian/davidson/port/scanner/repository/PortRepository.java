package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Port;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PortRepository extends JpaRepository<Port, Long> {

    @Modifying
    @Query(value = "delete from port where tenant_id = (:tenantId)", nativeQuery = true)
    void deleteAllByTenantId(@Param("tenantId") Long tenantId);

    @Query(value = "select * from port where tenant_id = (:tenantId) order by port asc", nativeQuery = true)
    Set<Port> findAllByTenantId(@Param("tenantId") Long tenantId);
}
