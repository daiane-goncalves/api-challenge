package msg.challenge.api.message;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "tb_message")
public class MessageInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String message;

    public MessageInfo(){
        super();
    }


    public MessageInfo(Long id, String message) {
        this();
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "message='" + message + '\'' +
                '}';
    }

    public void updateInfo(MessageUpdate data) {
        if (data.id != null){
            this.message = data.message;
        }
    }
}
