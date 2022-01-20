package cloud.cholewa.configaro.user.service;

import cloud.cholewa.configaro.exception.UserException;
import cloud.cholewa.configaro.exception.UserNotFoundException;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.user.dto.UserMapper;
import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
import cloud.cholewa.configaro.user.dto.UserUpdateRequest;
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
        return userMapper.convertToUserResponse(findUserById(id));
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::convertToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.delete(findUserById(userId));
    }

    @Override
    public UserResponse addUser(UserRequest userRequest) {
        UserEntity newUserEntity = UserEntity.builder()
                .firstname(userRequest.firstname())
                .lastname(userRequest.lastname())
                .email(findEmailWhenUserNotExists(userRequest.email()))
                .password(userRequest.password())
                .roleEntity(setUserRole(userRequest))
                .build();

        return userMapper.convertToUserResponse(userRepository.save(newUserEntity));
    }

    @Override
    public UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        UserEntity user = findUserById(userId);

        user.setFirstname(userUpdateRequest.firstname());
        user.setLastname(userUpdateRequest.lastname());
        user.setEmail(findEmailWhenUserNotExists(userUpdateRequest.email()));

        return userMapper.convertToUserResponse(userRepository.save(user));
    }

    @Override
    public String updateUserPassword(Long userId, String oldPassword, String newPassword) {
        UserEntity user = findUserById(userId);

        if (user.getPassword().equals(oldPassword)) {
            if (newPassword.length() < 8) {
                throw new UserException(ErrorDict.USER_PASSWORD_LENGTH);
            } else {
                user.setPassword(newPassword);
                userRepository.save(user);
                return "Password updated";
            }
        }
        throw new UserException(ErrorDict.USER_PASSWORD_UPDATE_ERROR);
    }

    @Override
    public UserResponse updateUserRole(Long userId, String newRoleName) {
        UserEntity user = findUserById(userId);
        user.setRoleEntity(
                roleRepository.findRoleByName(newRoleName)
                        .orElseGet(() -> roleRepository.findRoleByName("user")
                                .orElseThrow(() -> new UserException(ErrorDict.USER_ROLE_INVALID))));

        return userMapper.convertToUserResponse(userRepository.save(user));
    }

    private UserEntity findUserById(Long userId) {
        return userRepository.findUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(ErrorDict.USER_ID_NOT_EXISTS));
    }

    private String findEmailWhenUserNotExists(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new UserException(ErrorDict.USER_EMAIL_EXISTS);
        }
        return email;
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
