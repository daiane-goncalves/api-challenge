package msg.challenge.api.message;

import com.fasterxml.jackson.annotation.JsonProperty;

import msg.challenge.api.model.MessageModel;

public record MessageDTO(@JsonProperty("id") Long idParamDTO, @JsonProperty("message") String messageParamDTO) {

    public static MessageDTO fromModel(MessageModel messageModel) {
        return new MessageDTO(messageModel.getId(), messageModel.getMessage());
    }
    
}
