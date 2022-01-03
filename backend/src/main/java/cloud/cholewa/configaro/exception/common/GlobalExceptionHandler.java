package cloud.cholewa.configaro.exception.common;

import cloud.cholewa.configaro.exception.UserException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ErrorResponse handleBindException(BindException bindException, HttpServletRequest request) {
        List<String> errorMessages = bindException.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        return new ErrorResponse(getSource(request), errorMessages);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorResponse handleUserException(final UserException userException, final HttpServletRequest request) {
        return new ErrorResponse(getSource(request), List.of(userException.getMessage()));
    }

    private String getSource(HttpServletRequest request) {
        String DEFAULT_SOURCE_ERROR_MESSAGE = "Unknown source";

        try {
            return request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        } catch (Exception e) {
            return DEFAULT_SOURCE_ERROR_MESSAGE;
        }
    }
}
