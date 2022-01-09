package cloud.cholewa.configaro.user.controller;

import cloud.cholewa.configaro.BaseIntegrationTest;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.exception.common.ErrorResponse;
import cloud.cholewa.configaro.user.dto.UserRequest;
import cloud.cholewa.configaro.user.dto.UserResponse;
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

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerITests extends BaseIntegrationTest {

    private UserRequest userRequest;

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
}
