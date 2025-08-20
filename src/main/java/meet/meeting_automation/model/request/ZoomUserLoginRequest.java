package meet.meeting_automation.model.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZoomUserLoginRequest {
    private String email;
}
