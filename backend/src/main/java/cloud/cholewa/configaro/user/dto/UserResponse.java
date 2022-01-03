package cloud.cholewa.configaro.user.dto;

public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) {
}
