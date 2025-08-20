package meet.meeting_automation.service;

import org.springframework.http.ResponseEntity;

public interface ZoomUserGetInfoService {

    ResponseEntity<?> getZoomUserInfo(
            final String token
    );
}
