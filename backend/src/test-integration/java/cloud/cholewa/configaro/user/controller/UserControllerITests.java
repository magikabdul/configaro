package cloud.cholewa.configaro.user.controller;

import cloud.cholewa.configaro.BaseIntegrationTest;
import cloud.cholewa.configaro.exception.common.ErrorDict;
import cloud.cholewa.configaro.exception.common.ErrorResponse;
import cloud.cholewa.configaro.user.dto.UserRequest;
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

import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(mvcResult.getResponse().getContentAsString().contains("\"role\":\"user\""));    }

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
}
