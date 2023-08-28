package msg.challenge.api.controller;

import jakarta.validation.Valid;
import msg.challenge.api.message.MessageInfo;
import msg.challenge.api.message.MessageRepository;
import msg.challenge.api.message.MessageUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    MessageRepository repository;
    @PostMapping
    public MessageInfo insert(@RequestBody MessageInfo message){
        return repository.save(message);
    }

    @GetMapping
    public List<MessageInfo> findAll(){
        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public MessageInfo findById(@PathVariable Long id) {
        return repository.findById(id).get();
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable @Valid MessageUpdate message) {
        var messageInfo = repository.getReferenceById(message.id);
        messageInfo.updateInfo(message);
    }

//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//    }

}
