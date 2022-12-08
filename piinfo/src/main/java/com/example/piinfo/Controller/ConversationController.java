package com.example.piinfo.Controller;

import com.example.piinfo.model.Conversation;
import com.example.piinfo.model.Movement;
import com.example.piinfo.service.AccountService;
import com.example.piinfo.service.ConversationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("conversation")
public class ConversationController {

    private final ConversationService conversationService;
    private final AccountService accountService;

    public ConversationController(ConversationService conversationService, AccountService accountService) {
        this.conversationService = conversationService;
        this.accountService = accountService;
    }

    //Obtener todas las cuentas
    @GetMapping(value="/all")
    public List<Conversation> getAll(){
        List<Conversation> conversations = conversationService.getAll();
        return conversations;
    }

    //Seleccionar una cuenta
    @GetMapping(value="/find/{id}")
    public Conversation find(@PathVariable String id){
        return conversationService.get(id);
    }


    @PostMapping(value="/save/{id}")
    public ResponseEntity<String> save(@PathVariable String id, @RequestParam ("message") String message,
                                       @RequestParam ("who") String who){

        Conversation conversation = conversationService.save(message,who);
        accountService.saveConversation(id,conversation);

        String info = "Mensaje creado";

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

    @GetMapping(value="/getAll/{id}")
    public List<Conversation> getAllUserConversation(@PathVariable String id){
        List<Conversation> UserConversation = accountService.getAllUserConversation(id);
        return UserConversation;
    }


}
