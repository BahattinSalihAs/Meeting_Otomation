package meet.meeting_automation.service;

import meet.meeting_automation.model.request.ZoomUserLoginRequest;
public interface ZoomUserLoginService {

    void loginZoomUser(
            final ZoomUserLoginRequest zoomUserLoginRequest
            );
}
