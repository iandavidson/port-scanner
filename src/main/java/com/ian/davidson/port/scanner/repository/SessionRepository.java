package com.ian.davidson.port.scanner.repository;

import com.ian.davidson.port.scanner.model.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    @Modifying
    @Query(value = "delete from session where tenant_id = (:tenantId)", nativeQuery = true)
    void deleteAllByTenantId(@Param("tenantId") Long tenantId);
}
