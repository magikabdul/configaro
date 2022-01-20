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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private final Long ID = 1L;
    private final String FIRST_NAME = "John";
    private final String LAST_NAME = "Doe";
    private final String EMAIL = "john.doe@example.com";
    private final String PASSWORD = "12345678";
    private final String NEW_PASSWORD = "87654321";

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private static UserEntity userEntity;
    private static UserResponse userResponse;
    private static UserRequest userRequest;
    private static UserUpdateRequest userUpdateRequest;
    private static final RoleEntity roleAdmin = new RoleEntity(1L, "admin");
    private static final RoleEntity roleUser = new RoleEntity(2L, "user");

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder()
                .id(ID)
                .firstname(FIRST_NAME)
                .lastname(LAST_NAME)
                .email(EMAIL)
                .password(PASSWORD)
                .build();
    }

    @Test
    void shouldAddUserWithRoleAdmin() {
        userEntity.setRoleEntity(roleAdmin);

        userRequest = new UserRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, "admin");
        userResponse = new UserResponse(ID, FIRST_NAME, LAST_NAME, EMAIL, roleAdmin.getName());

        when(userRepository.findUserByEmail(EMAIL)).thenReturn(Optional.empty());
        when(roleRepository.findRoleByName("admin")).thenReturn(Optional.of(roleAdmin));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);

        UserResponse addedAdminUser = userService.addUser(userRequest);

        assertEquals(addedAdminUser.getRole(), roleAdmin.getName());
        verify(userRepository, times(1)).findUserByEmail(anyString());
        verify(roleRepository, times(1)).findRoleByName(roleAdmin.getName());
        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).convertToUserResponse(any());
    }

    @Test
    void shouldAddUserWithRoleUser() {
        userEntity.setRoleEntity(roleUser);

        userRequest = new UserRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, "user");
        userResponse = new UserResponse(ID, FIRST_NAME, LAST_NAME, EMAIL, roleUser.getName());

        when(userRepository.findUserByEmail(EMAIL)).thenReturn(Optional.empty());
        when(roleRepository.findRoleByName("user")).thenReturn(Optional.of(roleUser));
        when(userRepository.save(any())).thenReturn(userEntity);
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);

        UserResponse addedAdminUser = userService.addUser(userRequest);

        assertEquals(addedAdminUser.getRole(), roleUser.getName());
        verify(userRepository, times(1)).findUserByEmail(anyString());
        verify(roleRepository, times(1)).findRoleByName(roleUser.getName());
        verify(userRepository, times(1)).save(any());
        verify(userMapper, times(1)).convertToUserResponse(any());
    }

    @Test
    void shouldThrowConflictWhenEmailExists() {
        userRequest = new UserRequest(FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, null);

        when(userRepository.findUserByEmail(EMAIL)).thenReturn(Optional.of(userEntity));

        assertThatThrownBy(() -> userService.addUser(userRequest))
                .isInstanceOf(UserException.class)
                .hasMessage(ErrorDict.USER_EMAIL_EXISTS);

        verify(userRepository, times(1)).findUserByEmail(EMAIL);
    }

    @Test
    void shouldReturnEmptyListForGetAllUsers() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        int size = userService.getAllUsers().size();

        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(0)).convertToUserResponse(any());

        assertEquals(0, size);
    }

    @Test
    void shouldReturnListWithOneUserWhenGetUserByRoleUser() {
        String roleName = "user";
        userEntity.setRoleEntity(roleUser);
        List<UserEntity> users = List.of(userEntity);
        userResponse = new UserResponse(1L, FIRST_NAME, LAST_NAME, EMAIL, roleName);

        when(roleRepository.findRoleByName(roleName)).thenReturn(Optional.of(roleUser));
        when(userRepository.findUserByRoleEntity_Name(roleName)).thenReturn(users);
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);

        int size = userService.getUsersByRole(roleName).size();

        verify(userRepository, times(1)).findUserByRoleEntity_Name(roleName);
        verify(userMapper, times(1)).convertToUserResponse(userEntity);

        assertEquals(1, size);
    }

    @Test
    void shouldReturnListWithOneUserWhenGetUserByRoleAdmin() {
        String roleName = "admin";
        userEntity.setRoleEntity(roleAdmin);
        List<UserEntity> users = List.of(userEntity);
        userResponse = new UserResponse(1L, FIRST_NAME, LAST_NAME, EMAIL, roleName);

        when(roleRepository.findRoleByName(roleName)).thenReturn(Optional.of(roleAdmin));
        when(userRepository.findUserByRoleEntity_Name(roleName)).thenReturn(users);
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);

        int size = userService.getUsersByRole(roleName).size();

        verify(userRepository, times(1)).findUserByRoleEntity_Name(roleName);
        verify(userMapper, times(1)).convertToUserResponse(userEntity);

        assertEquals(1, size);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenGetUserByRoleWhenRoleNotExists() {
        String roleName = "test";

        when(roleRepository.findRoleByName(roleName)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUsersByRole(roleName))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(ErrorDict.USER_ROLE_NOT_EXISTS);

        verify(roleRepository, times(1)).findRoleByName(roleName);
    }

    @Test
    void shouldThrowUserNotFoundWhenUsersIdNotExists() {
        when(userRepository.findUserById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> userService.getUsersById(ID))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(ErrorDict.USER_ID_NOT_EXISTS);
    }

    @Test
    void shouldResponseOkWhenUserIsFoundById() {
        userResponse = UserResponse.builder()
                .id(ID)
                .firstname(FIRST_NAME)
                .lastname(LAST_NAME)
                .email(EMAIL)
                .build();

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);

        UserResponse userResponse = userService.getUsersById(ID);

        assertThat(userResponse)
                .isInstanceOf(UserResponse.class)
                .extracting("id").isEqualTo(1L);

        verify(userRepository, times(1)).findUserById(ID);
        verify(userMapper, times(1)).convertToUserResponse(userEntity);
    }

    @Test
    void shouldNotDeleteAndThrowUserNotFoundWhenUsersIdNotExists() {
        assertThatThrownBy(() -> userService.deleteUserById(ID))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(ErrorDict.USER_ID_NOT_EXISTS);

        verify(userRepository, times(1)).findUserById(ID);
    }

    @Test
    void shouldDeleteUserWhenUsersIdExists() {
        userResponse = UserResponse.builder()
                .id(ID)
                .build();

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));

        userService.deleteUserById(ID);

        verify(userRepository, times(1)).findUserById(ID);
        verify(userRepository, times(1)).delete(userEntity);
    }

    @Test
    void shouldNotUpdateUserAndThrowUserNotFoundWhenUserIdNotExists() {
        userUpdateRequest = new UserUpdateRequest(FIRST_NAME, LAST_NAME, EMAIL);

        assertThatThrownBy(() -> userService.updateUser(ID, userUpdateRequest))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(ErrorDict.USER_ID_NOT_EXISTS);

        verify(userRepository, times(1)).findUserById(ID);
        verify(userMapper, times(0)).convertToUserResponse(any());
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void shouldUpdateUserWhenUserIdExists() {
        userUpdateRequest = new UserUpdateRequest(FIRST_NAME, LAST_NAME, EMAIL);

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        userService.updateUser(ID, userUpdateRequest);

        verify(userRepository, times(1)).findUserById(ID);
        verify(userMapper, times(1)).convertToUserResponse(userEntity);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void shouldNotUpdateUserPasswordWhenUserIdNotExists() {
        assertThatThrownBy(() -> userService.updateUserPassword(ID, PASSWORD, NEW_PASSWORD))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(ErrorDict.USER_ID_NOT_EXISTS);

        verify(userRepository, times(1)).findUserById(ID);
        verify(userRepository, times(0)).save(any());
    }

    @Test
    void shouldNotUpdateUserPasswordWhenPasswordsDoesntMatch() {
        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));

        assertThatThrownBy(() -> userService.updateUserPassword(ID, NEW_PASSWORD, NEW_PASSWORD))
                .isInstanceOf(UserException.class)
                .hasMessage(ErrorDict.USER_PASSWORD_UPDATE_ERROR);

        verify(userRepository, times(1)).findUserById(ID);
    }

    @Test
    void shouldNotUpdateUserPasswordWhenNewPasswordTooShort() {
        String TOO_SHORT_PASS = "1234567";

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));

        assertThatThrownBy(() -> userService.updateUserPassword(ID, PASSWORD, TOO_SHORT_PASS))
                .isInstanceOf(UserException.class)
                .hasMessage(ErrorDict.USER_PASSWORD_LENGTH);

        verify(userRepository, times(1)).findUserById(ID);
    }

    @Test
    void shouldUpdateUserPassword() {
        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        userService.updateUserPassword(ID, PASSWORD, NEW_PASSWORD);

        verify(userRepository, times(1)).findUserById(ID);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void shouldNotUpdateUserRoleWhenUserIdNotExists() {
        assertThatThrownBy(() -> userService.updateUserRole(ID, roleAdmin.getName()))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessage(ErrorDict.USER_ID_NOT_EXISTS);

        verify(userRepository, times(1)).findUserById(ID);
    }

    @Test
    void shouldUpdateUserRoleWhenNewRoleIsAdmin() {
        String newRoleAdmin = "admin";

        userResponse = UserResponse.builder()
                .role(roleAdmin.getName())
                .build();

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));
        when(roleRepository.findRoleByName(newRoleAdmin)).thenReturn(Optional.of(roleAdmin));
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserResponse response = userService.updateUserRole(ID, newRoleAdmin);

        assertEquals(newRoleAdmin, response.getRole());

        verify(userRepository, times(1)).findUserById(ID);
        verify(roleRepository, times(1)).findRoleByName(newRoleAdmin);
        verify(userMapper, times(1)).convertToUserResponse(userEntity);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void shouldUpdateUserRoleWhenNewRoleIsUser() {
        String newRoleUser = "user";

        userResponse = UserResponse.builder()
                .role(roleUser.getName())
                .build();

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));
        when(roleRepository.findRoleByName(newRoleUser)).thenReturn(Optional.of(roleAdmin));
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserResponse response = userService.updateUserRole(ID, newRoleUser);

        assertEquals(newRoleUser, response.getRole());

        verify(userRepository, times(1)).findUserById(ID);
        verify(roleRepository, times(1)).findRoleByName(newRoleUser);
        verify(userMapper, times(1)).convertToUserResponse(userEntity);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void shouldUpdateUserRoleWhenNewRoleIsAny() {
        String newRoleAny = "er";

        userResponse = UserResponse.builder()
                .role(roleUser.getName())
                .build();

        when(userRepository.findUserById(ID)).thenReturn(Optional.of(userEntity));
        when(roleRepository.findRoleByName(newRoleAny)).thenReturn(Optional.empty());
        when(roleRepository.findRoleByName(roleUser.getName())).thenReturn(Optional.of(roleUser));
        when(userMapper.convertToUserResponse(userEntity)).thenReturn(userResponse);
        when(userRepository.save(userEntity)).thenReturn(userEntity);

        UserResponse response = userService.updateUserRole(ID, newRoleAny);

        assertEquals(roleUser.getName(), response.getRole());

        verify(userRepository, times(1)).findUserById(ID);
        verify(roleRepository, times(1)).findRoleByName(newRoleAny);
        verify(roleRepository, times(1)).findRoleByName(roleUser.getName());
        verify(userMapper, times(1)).convertToUserResponse(userEntity);
        verify(userRepository, times(1)).save(userEntity);
    }
}
