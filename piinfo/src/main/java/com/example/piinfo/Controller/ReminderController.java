package com.example.piinfo.Controller;

import com.example.piinfo.model.Movement;
import com.example.piinfo.model.Reminder;
import com.example.piinfo.service.AccountService;
import com.example.piinfo.service.ReminderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("reminder")
public class ReminderController {

    private final ReminderService reminderService;
    private final AccountService accountService;

    public ReminderController(ReminderService reminderService, AccountService accountService) {
        this.reminderService = reminderService;
        this.accountService = accountService;
    }

    //Obtener todas las cuentas
    @GetMapping(value="/all")
    public List<Reminder> getAll(){
        List<Reminder> reminders = reminderService.getAll();
        return reminders;
    }

    //Seleccionar una cuenta
    @GetMapping(value="/find/{id}")
    public Reminder find(@PathVariable String id){
        return reminderService.get(id);
    }


    @PostMapping(value="/save/{id}")
    public ResponseEntity<String> save(@PathVariable String id, @RequestParam ("description") String description,
                                         @RequestParam ("date") String date){

        String info = reminderService.verify(date);
        if(info.equals("El recordatorio esta estructurado")){
                Reminder reminder = reminderService.save(description,date);
                accountService.saveReminder(id,reminder);
        }

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

    @GetMapping(value="/doReminder/{id}")
    public ResponseEntity<String> doReminder(@PathVariable String id){
        String info = accountService.doReminder(id);

        if(!info.equals("null")){
            reminderService.putDone(info);
        }

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

    @GetMapping(value="/getAll/{id}")
    public List<Reminder> getAllUseReminder(@PathVariable String id){
        List<Reminder> UserReminder = accountService.getAllUserReminder(id);
        return UserReminder;
    }

}
