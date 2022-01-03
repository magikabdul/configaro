package cloud.cholewa.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public record UserRequest(
        @NotNull
        @NotBlank
        @Size(min = 3)
        String firstname,

        @NotNull
        @NotBlank
        @Size(min = 3)
        String lastname,

        @Email
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        @Size(min = 8)
        String password,

        String role
) {
}
