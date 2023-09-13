package msg.challenge.api.controller;

import jakarta.validation.Valid;
import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

//criar exceção personalizada
//controller advice (json amigavel)
//se colocar letra no getid postman
//tratar id no put (pois pode ir numeros que nao existem e texto)
//retirar id da repsosta do post
//tratar letra no id do delete postman

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid MessageDTO messageDTO, UriComponentsBuilder uriBuilder) {
        messageService.inputMessage(messageDTO);

        var uri = uriBuilder.path("/messages/{id}").buildAndExpand(messageDTO.idParamDTO()).toUri();
        return ResponseEntity.created(uri).body(messageDTO);
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
