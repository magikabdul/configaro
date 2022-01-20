package cloud.cholewa.configaro.user.controller;

import cloud.cholewa.configaro.BaseIntegrationTest;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.exception.common.ErrorResponse;
import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
import cloud.cholewa.configaro.user.dto.UserUpdateRequest;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class UserControllerITests extends BaseIntegrationTest {

    private UserRequest userRequest;
    private final Map<String, String> stringMap = new HashMap<>();

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenBodyIsMissing() {
        MvcResult mvcResult = mvc.perform(post("/users"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().get(0).contains("Required request body is missing"));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenBodyIsEmpty() {
        mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenFirstnameIsMissing() {
        userRequest = new UserRequest(null, "Doe", "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_FIRSTNAME_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenFirstnameIsEmpty() {
        userRequest = new UserRequest("", "Doe", "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_FIRSTNAME_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenFirstnameIsTooShort() {
        userRequest = new UserRequest("Jo", "Doe", "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_FIRSTNAME_LENGTH));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenLastnameIsMissing() {
        userRequest = new UserRequest("Joe", null, "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_LASTNAME_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenLastnameIsEmpty() {
        userRequest = new UserRequest("Joe", "", "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_LASTNAME_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenLastnameIsTooShort() {
        userRequest = new UserRequest("Joe", "Do", "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_LASTNAME_LENGTH));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenEmailIsInvalid() {
        userRequest = new UserRequest("Joe", "Do", "john.doe.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_EMAIL_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenPasswordIsMissing() {
        userRequest = new UserRequest("Joe", "Doe", "john.doe@example.com", null, null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_PASSWORD_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenPasswordIsEmpty() {
        userRequest = new UserRequest("Joe", "Doe", "john.doe@example.com", "", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_PASSWORD_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenPasswordIsTooShort() {
        userRequest = new UserRequest("Joe", "Doe", "john.doe@example.com", "1234567", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_PASSWORD_LENGTH));
    }

    @Test
    @SneakyThrows
    void shouldResponseWithBadRequestWhenEmailExists() {
        userRequest = new UserRequest("Joe", "Doe", "john.doe@example.com", "12345678", null);

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)));

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8), ErrorResponse.class);

        assertTrue(errorResponse.message().contains(ErrorDict.USER_EMAIL_EXISTS));
    }

    @Test
    @SneakyThrows
    public void shouldAddUserWithRoleUser() {
        userRequest = new UserRequest("Joe", "Doe", "john.doe@example.com", "12345678", null);

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getHeaders("Location").get(0).contains("/users/"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"role\":\"user\""));
    }

    @Test
    @SneakyThrows
    public void shouldAddUserWithRoleAdmin() {
        userRequest = new UserRequest("Joan", "Dawson", "joan.dawson@example.com", "12345678", "admin");

        MvcResult mvcResult = mvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        assertTrue(mvcResult.getResponse().getHeaders("Location").get(0).contains("/users/"));
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"role\":\"admin\""));
    }

    @Test
    @SneakyThrows
    void shouldResponseOkWhenGetAllUsers() {
        MvcResult mvcResult = mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<UserResponse> users = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertThat(users)
                .isNotEmpty()
                .map(UserResponse::getRole).allMatch(role -> role.equals("admin") || role.equals("user"))
                .hasSizeGreaterThanOrEqualTo(20);
    }

    @Test
    @SneakyThrows
    void shouldResponseNotFoundWhenGetUsersByRoleWhichNotExists() {
        mvc.perform(get("/users)").param("role", "test"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void shouldResponseOkWhenGetUsersByRoleAdmin() {
        MvcResult mvcResult = mvc.perform(get("/users").param("role", "admin"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<UserResponse> users = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertThat(users)
                .isNotEmpty()
                .map(UserResponse::getRole).allMatch(role -> role.equals("admin"))
                .hasSizeGreaterThanOrEqualTo(3);
    }

    @Test
    @SneakyThrows
    void shouldResponseOkWhenGetUsersByRoleUser() {
        MvcResult mvcResult = mvc.perform(get("/users").param("role", "user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        List<UserResponse> users = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
                });

        assertThat(users)
                .isNotEmpty()
                .map(UserResponse::getRole).allMatch(s -> s.equals("user"))
                .hasSizeGreaterThanOrEqualTo(17);
    }

    @Test
    @SneakyThrows
    void shouldResponseUserNotFoundWhenGetUserByIdButUserWithIdNotExists() {
        MvcResult mvcResult = mvc.perform(get("/users/1000"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse error = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(error)
                .extracting("message").isEqualTo(List.of(ErrorDict.USER_ID_NOT_EXISTS));
    }

    @Test
    @SneakyThrows
    void shouldResponseOkWhenGetUserById() {
        MvcResult mvcResult = mvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        UserResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), UserResponse.class);

        assertThat(response)
                .extracting("id").isEqualTo(1L);
    }

    @Test
    @SneakyThrows
    void shouldResponseNotFoundWhenDeleteUserByIdWhenIdNotExists() {
        MvcResult mvcResult = mvc.perform(delete("/users/1000"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse response = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(response)
                .extracting("message").isEqualTo(List.of(ErrorDict.USER_ID_NOT_EXISTS));
    }

    @Test
    @SneakyThrows
    void shouldResponseNoContentWhenDeleteUserByIdWhenIdExists() {
        mvc.perform(delete("/users/2"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenBodyIsMissing_ForUserUpdate() {
        MvcResult mvcResult = mvc.perform(put("/users/30"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(errorResponse)
                .extracting("message").asString().contains("Required request body is missing");
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenBodyIsEmpty_ForUserUpdate() {
        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(errorResponse)
                .extracting("message").asString()
                .contains(List.of(ErrorDict.USER_FIRSTNAME_BLANK, ErrorDict.USER_LASTNAME_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenBodyHasOnlyFirstname_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", null, null);

        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(errorResponse)
                .extracting("message").asString()
                .contains(List.of(ErrorDict.USER_LASTNAME_BLANK, ErrorDict.USER_EMAIL_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenBodyHasOnlyLastname_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("", null, null);

        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(errorResponse)
                .extracting("message")
                .asString()
                .contains(List.of(
                        ErrorDict.USER_FIRSTNAME_BLANK,
                        ErrorDict.USER_FIRSTNAME_LENGTH,
                        ErrorDict.USER_LASTNAME_BLANK,
                        ErrorDict.USER_EMAIL_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenFirstNameIsTooShort_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("Jo", "Doe", "john.doe@example.com");

        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class);

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_FIRSTNAME_LENGTH));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenLastNameIsTooShort_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", "Do", "john.doe@example.com");

        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_LASTNAME_LENGTH));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenEmailFieldIsMissing_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", "Doe", null);

        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_EMAIL_BLANK));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenEmailFieldIsNotRightEmailAddress_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", "Doe", "df");

        MvcResult mvcResult = mvc.perform(put("/users/30")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_EMAIL_INVALID));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenEmailExists_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", "Doe", "bsummersby0@shop-pro.jp");

        MvcResult mvcResult = mvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_EMAIL_EXISTS));
    }

    @Test
    @SneakyThrows
    void shouldResponseNotFound_WhenIdNotExistsAndHasBody_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", "Doe", "jd@example.com");

        MvcResult mvcResult = mvc.perform(put("/users/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_ID_NOT_EXISTS));
    }

    @Test
    @SneakyThrows
    void shouldResponseAccepted_AndReturnUpdatedUser_ForUserUpdate() {
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest("John", "Doe", "john.doe@example.com");

        MvcResult mvcResult = mvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userUpdateRequest)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        UserResponse userResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), UserResponse.class
        );

        assertThat(userResponse)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("firstname", "John")
                .hasFieldOrPropertyWithValue("lastname", "Doe")
                .hasFieldOrPropertyWithValue("email", "john.doe@example.com")
                .hasFieldOrPropertyWithValue("role", "admin");
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenBodyIsMissing_ForUserUpdateRoleOrPassword() {
        MvcResult mvcResult = mvc.perform(patch("/users/1"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .asString()
                .contains("Required request body is missing");
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenBodyIsEmpty_ForUserUpdateRoleOrPassword() {
        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_FIELD_NOT_EXISTS));
    }

    @Test
    @SneakyThrows
    void shouldResponseNotFound_WhenIdNotExists_ForUserUpdateRoleOrPassword() {
        stringMap.put("role", "user");

        MvcResult mvcResult = mvc.perform(patch("/users/1000")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_ID_NOT_EXISTS));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenOldPasswordIsInvalid_ForUserUpdateRoleOrPassword() {
        stringMap.put("oldPassword", "wrongOldPassword");
        stringMap.put("newPassword", "12345678");

        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_PASSWORD_UPDATE_ERROR));
    }

    @Test
    @SneakyThrows
    void shouldResponseBadRequest_WhenNewPasswordIsTooShort_ForUserUpdateRoleOrPassword() {
        stringMap.put("oldPassword", "1HxQYolRI5A4");
        stringMap.put("newPassword", "1234567");

        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();

        ErrorResponse errorResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), ErrorResponse.class
        );

        assertThat(errorResponse)
                .extracting("message")
                .isEqualTo(List.of(ErrorDict.USER_PASSWORD_LENGTH));
    }

    @Test
    @SneakyThrows
    void shouldResponseAccepted_WhenUpdatingPassword_ForUserUpdateRoleOrPassword() {
        stringMap.put("oldPassword", "1HxQYolRI5A4");
        stringMap.put("newPassword", "12345678");

        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        assertEquals("Password updated", mvcResult.getResponse().getContentAsString());
    }

    @Test
    @SneakyThrows
    void shouldResponseAccepted_WhenUpdatingRoleAndNewRoleIsAdmin_ForUserUpdateRoleOrPassword() {
        stringMap.put("role", "admin");

        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        UserResponse userResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), UserResponse.class
        );

        assertThat(userResponse)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("role", "admin");
    }

    @Test
    @SneakyThrows
    void shouldResponseAccepted_WhenUpdatingRoleAndNewRoleIsUser_ForUserUpdateRoleOrPassword() {
        stringMap.put("role", "user");

        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        UserResponse userResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), UserResponse.class
        );

        assertThat(userResponse)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("role", "user");

    }

    @Test
    @SneakyThrows
    void shouldResponseAccepted_WhenUpdatingRoleAndNewRoleIsAny_ForUserUpdateRoleOrPassword() {
        stringMap.put("role", "");

        MvcResult mvcResult = mvc.perform(patch("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stringMap)))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andReturn();

        UserResponse userResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), UserResponse.class
        );

        assertThat(userResponse)
                .hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("role", "user");
    }
}
