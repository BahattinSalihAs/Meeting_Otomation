package meet.meeting_automation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import meet.meeting_automation.model.entity.ZoomUser;
import meet.meeting_automation.model.request.ZoomUserLoginRequest;
import meet.meeting_automation.model.request.ZoomUserRegisterRequest;
import meet.meeting_automation.repo.ZoomUserRepository;
import meet.meeting_automation.service.ZoomUserGenerateMeetingService;
import meet.meeting_automation.service.ZoomUserGenerateTokenService;
import meet.meeting_automation.service.ZoomUserLoginService;
import meet.meeting_automation.service.ZoomUserRegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/zoom")
@RequiredArgsConstructor
public class ZoomUserController {

    private final ZoomUserRegisterService zoomUserRegisterService;
    private final ZoomUserLoginService zoomUserLoginService;
    private final ZoomUserRepository zoomUserRepository;
    private final ZoomUserGenerateTokenService zoomUserGenerateTokenService;
    private final ZoomUserGenerateMeetingService zoomUserGenerateMeetingService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerZoomUser(
            @RequestBody@Valid final ZoomUserRegisterRequest request
    ) {
        zoomUserRegisterService.registerZoomUser(request);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginZoomUser(
            @RequestBody@Valid final ZoomUserLoginRequest request
    ) {
        zoomUserLoginService.loginZoomUser(request);
        return ResponseEntity.status(200).build();
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/generate-meeting")
    public String showGenerateMeetingPage() {
        return "generate-meeting";
    }

    @GetMapping("/getUserData")
    public ResponseEntity<ZoomUser> getUserData(
            @RequestParam final String email
    ) {
        final ZoomUser zoomUserFromDB = zoomUserRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(zoomUserFromDB);
    }

    @PostMapping("/getToken")
    public ResponseEntity<Map<String, String>> generateZoomUserToken(
            @RequestBody final String email
    ) {
        final String token = zoomUserGenerateTokenService.generateToken(email);
        Map<String, String> response = new HashMap<>();
        response.put("access_token", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/createMeeting")
    public ResponseEntity<String> createMeeting(
            @RequestParam("token") final String token,
            @RequestBody Map<String, Object> meetingData
    ) {
        try {
            System.out.println("Received meeting data: " +  meetingData);

            if (meetingData.isEmpty()) {
                System.out.println("Received empty meeting data.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Meeting data is empty.");
            }

            final String zoomResponse = zoomUserGenerateMeetingService.generateMeeting(token, meetingData);

            return ResponseEntity.ok(zoomResponse);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creatin Zoom meeting: " + e.getMessage());
        }
    }
}


















