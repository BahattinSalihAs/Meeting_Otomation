package meet.meeting_automation.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import meet.meeting_automation.repo.ZoomUserRepository;
import meet.meeting_automation.service.ZoomUserGetInfoService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ZoomUserGetInfoServiceImpl implements ZoomUserGetInfoService {

    private final ZoomUserRepository zoomUserRepository;

    @Override
    public ResponseEntity<?> getZoomUserInfo(
            final String token
    ) {
        String url = "https://api-us.zoom.us/v2/users/me";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        headers.set("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Get Zoom User Info successfully");
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(response.getBody());
                return ResponseEntity.ok().body(jsonNode);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
