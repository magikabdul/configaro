package cloud.cholewa.user.service;

import cloud.cholewa.user.dto.UserRequest;
import cloud.cholewa.user.dto.UserResponse;

public interface UserService {

    UserResponse addUser(UserRequest userRequest);
}
