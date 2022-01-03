package cloud.cholewa.user.service;

import cloud.cholewa.user.dto.UserMapper;
import cloud.cholewa.user.dto.UserRequest;
import cloud.cholewa.user.dto.UserResponse;
import cloud.cholewa.user.model.RoleEntity;
import cloud.cholewa.user.model.UserEntity;
import cloud.cholewa.user.repository.RoleRepository;
import cloud.cholewa.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

            UserEntity userEntity = userRepository.save(newUserEntity);
            return userMapper.mapToUserResponse(userEntity);
        }
        throw new IllegalArgumentException("mail exist");
    }

    private RoleEntity setUserRole(UserRequest userRequest) {
        Optional<RoleEntity> role = roleRepository.findRoleByName(userRequest.role());

        if (userRequest.role() == null || role.isEmpty()) {
            return roleRepository.findRoleByName("user").orElseThrow(() -> new IllegalArgumentException("role does not exists"));
        }
        return roleRepository.findRoleByName("admin").orElseThrow(() -> new IllegalArgumentException("role does not exists"));
    }
}
