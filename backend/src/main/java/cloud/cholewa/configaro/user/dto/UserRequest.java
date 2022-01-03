package cloud.cholewa.configaro.user.dto;

import cloud.cholewa.configaro.exception.common.ErrorDict;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRequest(
        @NotBlank(message = ErrorDict.USER_FIRSTNAME_BLANK)
        @Size(min = 3, message = ErrorDict.USER_FIRSTNAME_LENGTH)
        String firstname,

        @NotBlank(message = ErrorDict.USER_LASTNAME_BLANK)
        @Size(min = 3, message = ErrorDict.USER_LASTNAME_LENGTH)
        String lastname,

        @Email(message = ErrorDict.USER_EMAIL_INVALID)
        String email,

        @NotBlank(message = ErrorDict.USER_PASSWORD_BLANK)
        @Size(min = 8, message = ErrorDict.USER_PASSWORD_LENGTH)
        String password,

        String role
) {
}
