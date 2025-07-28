package works.joyboy.cvbuilder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralDocumentGenerationException extends RuntimeException {
    public GeneralDocumentGenerationException(String message) {
        super(message);
    }

    public GeneralDocumentGenerationException(String message, Throwable cause) {
        super(message, cause);
    }
}