package msg.challenge.api.controller;

import jakarta.validation.Valid;
import msg.challenge.api.message.MessageInfo;
import msg.challenge.api.message.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    MessageRepository repository;

    @PostMapping
    public MessageInfo insert(@RequestBody MessageInfo message) {
        return repository.save(message);
    }

    @GetMapping
    public List<MessageInfo> findAll() {
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public MessageInfo findById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable @Valid Long id, @RequestBody MessageInfo message) {
        var messageInfo = repository.getReferenceById(id);
        messageInfo.setMessage(message.getMessage());
        repository.save(messageInfo);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
