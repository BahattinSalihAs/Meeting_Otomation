package meet.meeting_automation.service;

public interface ZoomUserGenerateTokenService {

    String generateToken(
            final String email
    );
}
