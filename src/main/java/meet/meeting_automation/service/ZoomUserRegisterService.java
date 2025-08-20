package meet.meeting_automation.service;

import meet.meeting_automation.model.request.ZoomUserLoginRequest;
import meet.meeting_automation.model.request.ZoomUserRegisterRequest;

public interface ZoomUserRegisterService {

    void registerZoomUser(
            final ZoomUserRegisterRequest zoomUserRegisterRequest
    );
}
