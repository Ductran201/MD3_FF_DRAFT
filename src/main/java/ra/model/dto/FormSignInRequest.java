package ra.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FormSignInRequest {
    private String email;
    private String password;
}
