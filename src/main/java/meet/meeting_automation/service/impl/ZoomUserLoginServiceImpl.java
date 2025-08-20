package meet.meeting_automation.service.impl;

import lombok.RequiredArgsConstructor;
import meet.meeting_automation.model.entity.ZoomUser;
import meet.meeting_automation.model.request.ZoomUserLoginRequest;
import meet.meeting_automation.repo.ZoomUserRepository;
import meet.meeting_automation.service.ZoomUserLoginService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZoomUserLoginServiceImpl implements ZoomUserLoginService {
    private final ZoomUserRepository zoomUserRepository;

    @Override
    public void loginZoomUser(
            final ZoomUserLoginRequest zoomUserLoginRequest
    ) {
        final ZoomUser zoomUserFromDB = zoomUserRepository.findByEmail(zoomUserLoginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found!"));

    }
}
