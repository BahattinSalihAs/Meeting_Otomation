package meet.meeting_automation.service.impl;

import lombok.RequiredArgsConstructor;
import meet.meeting_automation.service.ZoomUserGenerateMeetingService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ZoomUserGenerateMeetingServiceImpl implements ZoomUserGenerateMeetingService {
    @Override
    public String generateMeeting(
            final String token,
            final Map<String, Object> meetingData
            ) {
        System.out.println("meetingData: " + meetingData);

        final String url = "https://api-us.zoom.us/v2/users/me/meetings";
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/json");

        final HttpEntity<Map<String, Object>> entity = new HttpEntity<>(meetingData, headers);
        final RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);


        return response.getBody();
    }
}
