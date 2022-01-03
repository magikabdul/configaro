package cloud.cholewa.configaro.user.dto;

import cloud.cholewa.configaro.user.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity mapToUserEntity(UserRequest userRequest) {
        return UserEntity
                .builder()
                .firstname(userRequest.firstname())
                .lastname(userRequest.lastname())
                .email(userRequest.email())
                .password(userRequest.password())
                .build();
    }

    public UserResponse mapToUserResponse(UserEntity userEntity) {
        return new UserResponse(
                userEntity.getId(),
                userEntity.getFirstname(),
                userEntity.getLastname(),
                userEntity.getEmail()
        );
    }
}
