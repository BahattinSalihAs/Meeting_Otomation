package meet.meeting_automation.model.request;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZoomUserRegisterRequest {
    private String email;
    private String clientId;
    private String clientSecret;
    private String accountId;
}
