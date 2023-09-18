package msg.challenge.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import msg.challenge.api.exception.ExceptionController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionControllerTest {

    @InjectMocks
    private ExceptionController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testError404() {
        // Arrange
        NoSuchElementException exception = new NoSuchElementException();

        // Act
        ResponseEntity response = controller.error404();

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testError400() {
        // Arrange
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        Set<ConstraintViolation<?>> errors = Set.of(mock(ConstraintViolation.class));
        when(exception.getConstraintViolations()).thenReturn(errors);

        // Act
        ResponseEntity response = controller.error400(exception);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}