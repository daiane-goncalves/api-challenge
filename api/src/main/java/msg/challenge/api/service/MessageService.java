package msg.challenge.api.service;

import msg.challenge.api.message.MessageDTO;
import msg.challenge.api.model.MessageModel;
import msg.challenge.api.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    MessageRepository repository;

    public Long inputMessage( MessageDTO messageDTO) {
        var message = new MessageModel();
        message.setMessage(messageDTO.messageParamDTO());
        repository.save(message);
        return message.getId();
    }

    public List<MessageDTO> getAll() {
        return repository.findAll().stream()
                .map(MessageDTO::fromModel)
                .collect(Collectors.toList());
    }

    public MessageDTO findById(Long id) {
        return repository.findById(id)
                .map(MessageDTO::fromModel)
                .orElseThrow();
    }

    public void update(MessageDTO messageDTO) {
        var optional = repository.findById(messageDTO.idParamDTO());
        if (optional.isPresent()) {
            var messageUpdate = optional.get();
            messageUpdate.setMessage(messageDTO.messageParamDTO());
            repository.save(messageUpdate);
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
