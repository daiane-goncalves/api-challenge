package msg.challenge.api.controller;

import jakarta.validation.Valid;
import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid MessageDTO messageDTO) {
        messageService.inputMessage(messageDTO);
        return ResponseEntity.created(null).body(messageDTO.messageParamDTO());
    }

    @GetMapping
    public ResponseEntity <List<MessageModel>> findAll() {
        var getAllMessages = messageService.getAll();
        return ResponseEntity.ok(getAllMessages);
    }

    @GetMapping(value = "/{id}")
    public MessageModel findById(@PathVariable Long id) {
        return messageService.findById(id);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody MessageDTO messageDTO) {
        messageService.update(messageDTO);
        return ResponseEntity.ok(messageDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        messageService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
