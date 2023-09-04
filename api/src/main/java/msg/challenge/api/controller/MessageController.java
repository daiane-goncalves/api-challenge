package msg.challenge.api.controller;

import jakarta.validation.Valid;
import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//criar exceção personalizada
//controller advice (json amigavel)

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping
    public void insert(@RequestBody @Valid MessageDTO messageDTO) {
        messageService.inputMessage(messageDTO);
    }

    @GetMapping
    public List<MessageModel> findAll() {
        return messageService.getAll();
    }

    @GetMapping(value = "/{id}")
    public MessageModel findById(@PathVariable Long id) {
        return messageService.findById(id);
    }

    @PutMapping
    public void update(@RequestBody MessageDTO messageDTO) {
        messageService.update(messageDTO);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        messageService.delete(id);
    }

}
