package com.ian.davidson.port.scanner.service;

import com.ian.davidson.port.scanner.exception.ResourceNotFoundException;
import com.ian.davidson.port.scanner.model.entity.Session;
import com.ian.davidson.port.scanner.repository.SessionRepository;
import com.ian.davidson.port.scanner.service.impl.SessionServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class SessionServiceTest {

    private static final Long SESSION_ID = 1L;
    private static final Long TENANT_ID = 100L;

    @Test
    void addSession() {
        Session session = mock(Session.class);

        SessionRepository sessionRepository = mock(SessionRepository.class);
        when(sessionRepository.save(session)).thenReturn(session);

        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        Session result = sessionService.addSession(session);

        assertThat(result).isNotNull();
        assertThat(result).isEqualTo(session);
        verify(sessionRepository).save(session);
    }

    @Test
    void getSession_success() {
        Session session = mock(Session.class);

        SessionRepository sessionRepository = mock(SessionRepository.class);
        when(sessionRepository.findById(SESSION_ID)).thenReturn(Optional.of(session));

        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        Session result = sessionService.getSession(SESSION_ID);

        assertThat(result).isNotNull();
        verify(sessionRepository).findById(SESSION_ID);
    }

    @Test
    void getSession_notFound() {
        SessionRepository sessionRepository = mock(SessionRepository.class);
        when(sessionRepository.findById(SESSION_ID)).thenReturn(Optional.empty());

        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        assertThatThrownBy(() -> sessionService.getSession(SESSION_ID)).hasMessage("Could not find session at id: " + SESSION_ID)
                .isInstanceOf(ResourceNotFoundException.class);

        verify(sessionRepository).findById(SESSION_ID);
    }

    @Test
    void deleteSessionsByTenantId() {
        SessionRepository sessionRepository = mock(SessionRepository.class);
        doNothing().when(sessionRepository).deleteAllByTenantId(TENANT_ID);

        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        sessionService.deleteSessionsByTenantId(TENANT_ID);

        verify(sessionRepository).deleteAllByTenantId(TENANT_ID);
    }

    @Test
    void deleteSession() {
        SessionRepository sessionRepository = mock(SessionRepository.class);
        doNothing().when(sessionRepository).deleteById(SESSION_ID);

        SessionService sessionService = new SessionServiceImpl(sessionRepository);
        sessionService.deleteSession(SESSION_ID);

        verify(sessionRepository).deleteById(SESSION_ID);
    }
}
