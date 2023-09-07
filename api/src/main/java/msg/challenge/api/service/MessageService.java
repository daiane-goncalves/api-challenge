package msg.challenge.api.service;

import msg.challenge.api.exception.NotFoundException;
import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageRepository repository;

    public void inputMessage( MessageDTO messageDTO) {
        var message = new MessageModel();
        message.setMessage(messageDTO.messageParamDTO());
        repository.save(message);
    }

    public List<MessageModel> getAll() {
        List<MessageModel> all = repository.findAll();
        return all;
    }

    public MessageModel findById(Long id) {
        var optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new NotFoundException();
        }
        return optional.get();
    }


    public void update(MessageDTO messageDTO) {
        var optional = repository.findById(messageDTO.idParamDTO());
        if (optional.isEmpty()) {
            throw new NotFoundException();
        }
        var messageUpdate = optional.get();
        messageUpdate.setMessage(messageDTO.messageParamDTO());
        repository.save(messageUpdate);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
