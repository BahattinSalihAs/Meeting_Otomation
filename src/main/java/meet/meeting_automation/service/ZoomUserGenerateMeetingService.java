package meet.meeting_automation.service;

import java.util.List;
import java.util.Map;

public interface ZoomUserGenerateMeetingService {

    String generateMeeting(
            final String token,
            final Map<String, Object> meetingData
    );
}
