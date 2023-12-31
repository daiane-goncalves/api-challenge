package msg.challenge.api.controller;

import jakarta.validation.Valid;
import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody @Valid MessageDTO messageDTO) {
        var id = messageService.inputMessage(messageDTO);
        URI uri = URI.create("messages/" + id);
        return ResponseEntity.created(uri).body(messageDTO.messageParamDTO());
    }


    @GetMapping
    public ResponseEntity<List<MessageDTO>> findAll() {
        var getAllMessages = messageService.getAll();
        return ResponseEntity.ok(getAllMessages);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MessageDTO> findById(@PathVariable Long id) {
        var getMessageById = messageService.findById(id);
        return ResponseEntity.ok().body(getMessageById);
    }

    @PutMapping
    public ResponseEntity<MessageDTO> update(@RequestBody MessageDTO messageDTO) {
        messageService.update(messageDTO);
        return ResponseEntity.ok(messageDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
