package cloud.cholewa.configaro.user.service;

import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
import cloud.cholewa.configaro.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {

    UserResponse addUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    List<UserResponse> getUsersByRole(String name);

    UserResponse getUsersById(Long id);

    void deleteUserById(Long userId);

    UserResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest);

    String updateUserPassword(Long userId, String oldPassword, String newPassword);

    UserResponse updateUserRole(Long userId, String newRoleName);
}
