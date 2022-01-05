package cloud.cholewa.configaro.user.service;

import cloud.cholewa.configaro.exception.UserException;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.user.dto.UserMapper;
import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
import cloud.cholewa.configaro.user.model.RoleEntity;
import cloud.cholewa.configaro.user.model.UserEntity;
import cloud.cholewa.configaro.user.repository.RoleRepository;
import cloud.cholewa.configaro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        if (userRepository.findUserByEmail(userRequest.email()).isEmpty()) {
            UserEntity newUserEntity = UserEntity.builder()
                    .firstname(userRequest.firstname())
                    .lastname(userRequest.lastname())
                    .email(userRequest.email())
                    .password(userRequest.password())
                    .roleEntity(setUserRole(userRequest))
                    .build();

            UserEntity ue = userRepository.save(newUserEntity);

            return userMapper.convertToUserResponse(ue);
        }
        throw new UserException(ErrorDict.USER_EMAIL_EXISTS);
    }

    private RoleEntity setUserRole(UserRequest userRequest) {

        if (userRequest.role().equalsIgnoreCase("admin")) {
            return roleRepository.findRoleByName("admin")
                    .orElseThrow(() -> new UserException(ErrorDict.USER_ROLE_INVALID));
        }

        return roleRepository.findRoleByName("user")
                .orElseThrow(() -> new UserException(ErrorDict.USER_ROLE_INVALID));
    }
}
