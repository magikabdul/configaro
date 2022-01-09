package cloud.cholewa.configaro.user.service;

import cloud.cholewa.configaro.exception.UserException;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.exception.common.UserNotFoundException;
import cloud.cholewa.configaro.user.dto.UserMapper;
import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
import cloud.cholewa.configaro.user.model.RoleEntity;
import cloud.cholewa.configaro.user.model.UserEntity;
import cloud.cholewa.configaro.user.repository.RoleRepository;
import cloud.cholewa.configaro.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> getUsersByRole(String name) {
        if (roleRepository.findRoleByName(name).isEmpty()) {
            throw new UserNotFoundException(ErrorDict.USER_ROLE_NOT_EXISTS);
        }

        return userRepository.findUserByRoleEntity_Name(name)
                .stream()
                .map(userMapper::convertToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUsersById(Long id) {
        return userRepository.findUserById(id)
                .map(userMapper::convertToUserResponse).
                orElseThrow(() -> new UserNotFoundException(ErrorDict.USER_ID_NOT_EXISTS));
    }

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

            return userMapper.convertToUserResponse(userRepository.save(newUserEntity));
        }
        throw new UserException(ErrorDict.USER_EMAIL_EXISTS);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::convertToUserResponse)
                .collect(Collectors.toList());
    }

    private RoleEntity setUserRole(UserRequest userRequest) {
        if (userRequest.role() == null) {
            return roleRepository.findRoleByName("user")
                    .orElseThrow(() -> new UserException(ErrorDict.USER_ROLE_INVALID));
        } else if (userRequest.role().equalsIgnoreCase("admin")) {
            return roleRepository.findRoleByName("admin")
                    .orElseThrow(() -> new UserException(ErrorDict.USER_ROLE_INVALID));
        }

        return roleRepository.findRoleByName("user")
                .orElseThrow(() -> new UserException(ErrorDict.USER_ROLE_INVALID));
    }
}
