package com.example.piinfo.Controller;

import com.example.piinfo.model.Conversation;
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

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
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

    //Guardar cuenta (no es parte del sistema, solo para probar)
    @PostMapping(value="/save")
    public ResponseEntity<Conversation> save(@RequestBody Conversation conversation){
        Conversation obj = conversationService.save(conversation);
        return new ResponseEntity<Conversation>(obj, HttpStatus.OK);
    }

}
