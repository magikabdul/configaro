package cloud.cholewa.configaro.user.dto;

import cloud.cholewa.configaro.user.model.RoleEntity;
import cloud.cholewa.configaro.user.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserMapperTest {

    @InjectMocks
    private UserMapper userMapper;

    private final RoleEntity roleEntity = new RoleEntity();
    private final Long ID = 1L;
    private final String FIRSTNAME = "John";
    private final String LASTNAME = "Doe";
    private final String EMAIL = "john.doe@example.com";
    private final String PASSWORD = "12345678";

    @Test
    void convertToUserResponseWhenRoleIsUser() {
        String roleUser = "user";
        roleEntity.setName(roleUser);

        UserEntity userEntity = UserEntity.builder()
                .id(ID)
                .firstname(FIRSTNAME)
                .lastname(LASTNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .roleEntity(roleEntity)
                .build();

        UserResponse userResponse = userMapper.convertToUserResponse(userEntity);

        assertEquals(ID, userResponse.getId());
        assertEquals(FIRSTNAME, userResponse.getFirstname());
        assertEquals(LASTNAME, userResponse.getLastname());
        assertEquals(EMAIL, userResponse.getEmail());
        assertEquals(roleUser, userResponse.getRole());
    }

    @Test
    void convertToUserResponseWhenRoleIsAdmin() {
        String roleAdmin = "admin";
        roleEntity.setName(roleAdmin);

        UserEntity userEntity = UserEntity.builder()
                .id(ID)
                .firstname(FIRSTNAME)
                .lastname(LASTNAME)
                .email(EMAIL)
                .password(PASSWORD)
                .roleEntity(roleEntity)
                .build();

        UserResponse userResponse = userMapper.convertToUserResponse(userEntity);

        assertEquals(ID, userResponse.getId());
        assertEquals(FIRSTNAME, userResponse.getFirstname());
        assertEquals(LASTNAME, userResponse.getLastname());
        assertEquals(EMAIL, userResponse.getEmail());
        assertEquals(roleAdmin, userResponse.getRole());
    }
}
