package meet.meeting_automation.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import meet.meeting_automation.model.entity.ZoomUser;
import meet.meeting_automation.model.request.ZoomUserRegisterRequest;
import meet.meeting_automation.repo.ZoomUserRepository;
import meet.meeting_automation.service.ZoomUserRegisterService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoomUserRegisterServiceImpl implements ZoomUserRegisterService {
    private final ZoomUserRepository zoomUserRepository;
    @Transactional
    @Override
    public void registerZoomUser(
            final ZoomUserRegisterRequest zoomUserRegisterRequest
    ) {
        if(zoomUserRepository.existsByEmail(zoomUserRegisterRequest.getEmail())) {
            throw new RuntimeException("User already registered!");
        }

        final ZoomUser zoomUser = ZoomUser.builder()
                .email(zoomUserRegisterRequest.getEmail().trim())
                .accountId(zoomUserRegisterRequest.getAccountId().trim())
                .clientId(zoomUserRegisterRequest.getClientId().trim())
                .clientSecret(zoomUserRegisterRequest.getClientSecret().trim())
                .build();

        zoomUserRepository.save(zoomUser);
    }
}
