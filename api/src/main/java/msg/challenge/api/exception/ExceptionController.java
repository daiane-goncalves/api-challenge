package msg.challenge.api.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity error404 (){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity error400 (ConstraintViolationException ex){
        var errors = ex.getConstraintViolations();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationData::new).toList());
    }

    private record ValidationData (String message){
        public ValidationData (ConstraintViolation error) {
            this(error.getPropertyPath() + " " + error.getMessage());
        }
    }
}
