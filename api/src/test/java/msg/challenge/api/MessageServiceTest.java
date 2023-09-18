package msg.challenge.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.repository.MessageRepository;
import msg.challenge.api.service.MessageService;

public class MessageServiceTest {

    @Mock
    private MessageRepository repository;

    @InjectMocks
    private MessageService service;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInputMessage() {
        // Arrange
        MessageDTO messageDTO = new MessageDTO(null, "Lalala");

        // Act
        service.inputMessage(messageDTO);

        // Assert
        verify(repository, times(1)).save(any(MessageModel.class));
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<MessageModel> messages = new ArrayList<>();
        messages.add(new MessageModel(1L, "Hello, world!"));
        messages.add(new MessageModel(2L, "Goodbye, world!"));
        when(repository.findAll()).thenReturn(messages);

        // Act
        List<MessageModel> result = service.getAll();

        // Assert
        assertEquals(messages, result);
    }

    @Test
    public void testFindById() {
        // Arrange
        MessageModel message = new MessageModel(1L, "Hello, world!");
        when(repository.findById(1L)).thenReturn(Optional.of(message));

        // Act
        MessageModel result = service.findById(1L);

        // Assert
        assertEquals(message, result);
    }

    @Test
    public void testUpdate() {
        // Arrange
        MessageDTO messageDTO = new MessageDTO(1L, "Test update");
        MessageModel message = new MessageModel(1L, "TERYT!");
        when(repository.findById(1L)).thenReturn(Optional.of(message));

        // Act
        service.update(messageDTO);

        // Assert
        verify(repository, times(1)).save(any(MessageModel.class));
        assertEquals("Test update", message.getMessage());
    }

    @Test
    public void testDelete() {
        // Arrange
        Long id = 1L;

        // Act
        service.delete(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }
}