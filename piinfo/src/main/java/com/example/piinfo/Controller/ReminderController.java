package com.example.piinfo.Controller;

import com.example.piinfo.model.Reminder;
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

    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
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

    //Guardar cuenta (no es parte del sistema, solo para probar)
    @PostMapping(value="/save")
    public ResponseEntity<Reminder> save(@RequestBody Reminder reminder){
        Reminder obj = reminderService.save(reminder);
        return new ResponseEntity<Reminder>(obj, HttpStatus.OK);
    }

}
