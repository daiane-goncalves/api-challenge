package msg.challenge.api.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //private static final String DELIMITER = ".";

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(BAD_REQUEST)
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        HttpStatus httpStatus;
        if (ex instanceof NotFoundException) {
            httpStatus = HttpStatus.NOT_FOUND;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        //String bodyOfResponse = ex.getMessage();
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
        return handleExceptionInternal(ex, errorResponse,
                new HttpHeaders(), httpStatus, request);
    }
}