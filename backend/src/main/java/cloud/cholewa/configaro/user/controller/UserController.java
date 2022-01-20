package cloud.cholewa.configaro.user.controller;

import cloud.cholewa.configaro.exception.UserException;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
import cloud.cholewa.configaro.user.dto.UserUpdateRequest;
import cloud.cholewa.configaro.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userService.addUser(userRequest);
        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequestUri()
                        .path("/" + userResponse.getId())
                        .build()
                        .toUri()).body(userResponse);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(params = "role")
    public ResponseEntity<List<UserResponse>> getUsersByRole(@RequestParam(name = "role") String role) {
        List<UserResponse> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUsersById(@PathVariable Long userId) {
        UserResponse user = userService.getUsersById(userId);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse user = userService.updateUser(userId, userUpdateRequest);
        return ResponseEntity.accepted().body(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<?> updateUserRoleOrPassword(@PathVariable Long userId, @RequestBody Map<String, String> body) {
        if (body.containsKey("role")) {
            return ResponseEntity.accepted().body(userService.updateUserRole(userId, body.get("role")));
        } else if (body.containsKey("oldPassword") && body.containsKey("newPassword")) {
            return ResponseEntity
                    .accepted()
                    .body(userService.updateUserPassword(userId, body.get("oldPassword"), body.get("newPassword")));
        }
        throw new UserException(ErrorDict.USER_FIELD_NOT_EXISTS);
    }
}
