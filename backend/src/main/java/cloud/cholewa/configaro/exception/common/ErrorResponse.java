package cloud.cholewa.configaro.exception.common;

import java.util.List;

public record ErrorResponse(String source, List<String> message) {
}
