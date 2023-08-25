package msg.challenge.api.controller;

import msg.challenge.api.message.MessageInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {
    @PostMapping(value = "/input")
    public void inputMessage(@RequestBody MessageInfo messageInfo){
        System.out.println(messageInfo);
    }
}
