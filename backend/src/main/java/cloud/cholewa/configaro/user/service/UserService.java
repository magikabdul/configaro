package cloud.cholewa.configaro.user.service;

import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;

public interface UserService {

    UserResponse addUser(UserRequest userRequest);
}
