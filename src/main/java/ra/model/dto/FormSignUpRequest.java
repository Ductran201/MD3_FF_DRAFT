package ra.model.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
@Builder
public class FormSignUpRequest {
//    @NotBlank(message = "Must not be blank")
    private String email;
//    @NotBlank(message = "Must not be blank")
    private String password;
    @NotBlank(message = "Must not be blank")
    private String fullName;
}
