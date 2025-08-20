package meet.meeting_automation.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import meet.meeting_automation.model.entity.ZoomUser;
import meet.meeting_automation.repo.ZoomUserRepository;
import meet.meeting_automation.service.ZoomUserGenerateTokenService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;


@Service
@RequiredArgsConstructor
public class ZoomUserGenerateTokenServiceImpl implements ZoomUserGenerateTokenService {
    private final ZoomUserRepository zoomUserRepository;
    @Override
    public String generateToken(
            final String email
    ) {
        String jsonString = email;

        // ObjectMapper ile JSON string'ini JsonNode'a çeviriyoruz
        ObjectMapper objectMapper = new ObjectMapper();
        // "email" alanını alıyoruz
        String eposta = "";
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonString);
            eposta = jsonNode.get("email").asText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final ZoomUser zoomUserFromDB = zoomUserRepository.findByEmail(eposta)
                .orElseThrow(() -> new RuntimeException("ZoomUser not found"));
        String url = "https://zoom.us/oauth/token?grant_type=account_credentials&account_id=" +  zoomUserFromDB.getAccountId();
        HttpHeaders headers = new HttpHeaders();
        String auth = zoomUserFromDB.getClientId() + ":" + zoomUserFromDB.getClientSecret();
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
        headers.set("Authorization", "Basic " + encodedAuth);
        headers.set("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        if(response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Token generated successfully");
            try {
                ObjectMapper objectMapper2 = new ObjectMapper();
                JsonNode jsonNode = objectMapper2.readTree(response.getBody());
                return jsonNode.get("access_token").asText();
            }catch (Exception e) {
                throw new RuntimeException("Failed to parse token response", e);
            }
        }else {
            throw new RuntimeException("Error generating token. Status code: " + response.getStatusCode());
        }
    }
}
