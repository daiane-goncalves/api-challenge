package msg.challenge.api.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Void> error404 (){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ValidationData>> error400 (ConstraintViolationException ex){
        var errors = ex.getConstraintViolations();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationData::new).toList());
    }

    public record ValidationData (String message){
        public ValidationData (ConstraintViolation<?> error) {
            this(error.getPropertyPath() + " " + error.getMessage());
        }
    }
}
