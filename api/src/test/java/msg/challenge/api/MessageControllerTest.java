package msg.challenge.api;

import msg.challenge.api.controller.MessageController;
import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsert() {
        // Arrange -> prepara ambiente para o teste
        MessageDTO messageDTO = new MessageDTO(null,"Test insert");

        // Mock the MessageService object
        Mockito.doNothing().when(messageService).inputMessage(messageDTO);

        // Act -> executa o teste
        ResponseEntity response = messageController.insert(messageDTO);

        // Assert -> verifica se o teste passou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        //assertNotNull(response.getHeaders().getLocation());
        assertEquals(messageDTO.messageParamDTO(), response.getBody());
        verify(messageService, times(1)).inputMessage(messageDTO);
    }

    @Test
    public void testFindAll() throws Exception {
        // Arrange
        List<MessageDTO> messages = new ArrayList<>();
        messages.add(new MessageDTO(1L, "Hello, world!"));
        messages.add(new MessageDTO(2L, "How are you?"));
        when(messageService.getAll()).thenReturn(messages);

        // Act
        ResponseEntity<List<MessageDTO>> response = messageController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(messages, response.getBody());
        verify(messageService, times(1)).getAll();
    }

    @Test
    public void testFindById() throws Exception {
        // Arrange
        Long messageId = 1L;
        MessageModel message = new MessageModel(messageId, "Hello, world!");
        when(messageService.findById(messageId)).thenReturn(MessageDTO.fromModel(message));

        // Act
        ResponseEntity<MessageDTO> response = messageController.findById(messageId);

        // Assert
        assertEquals(message, response);
        verify(messageService, times(1)).findById(messageId);
    }

    @Test
    public void testUpdate() throws Exception {
        // Arrange
        MessageDTO messageDTO = new MessageDTO(1L, "Test");

        // Act
        ResponseEntity response = messageController.update(messageDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(messageService, times(1)).update(messageDTO);
    }

    @Test
    public void testDelete() throws Exception {
        // Arrange
        Long messageId = 1L;

        // Act
        ResponseEntity response = messageController.delete(messageId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(messageService, times(1)).delete(messageId);
    }

}