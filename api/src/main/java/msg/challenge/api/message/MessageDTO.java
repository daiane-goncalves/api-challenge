package msg.challenge.api.message;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageDTO(@JsonProperty("id") Long idParamDTO, @JsonProperty("message") String messageParamDTO) {
}
