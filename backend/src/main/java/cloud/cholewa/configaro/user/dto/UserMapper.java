package cloud.cholewa.configaro.user.dto;

import cloud.cholewa.configaro.user.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse convertToUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .firstname(userEntity.getFirstname())
                .lastname(userEntity.getLastname())
                .email(userEntity.getEmail())
                .role(userEntity.getRoleEntity().getName())
                .build();
    }
}
