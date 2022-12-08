package com.example.piinfo.Controller;


import com.example.piinfo.model.Movement;
import com.example.piinfo.model.Reminder;
import com.example.piinfo.service.AccountService;
import com.example.piinfo.service.MovementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("movement")
public class MovementController {

    private final MovementService movementService;
    private final AccountService accountService;

    public MovementController(MovementService movementService, AccountService accountService) {
        this.movementService = movementService;
        this.accountService = accountService;
    }

    //Obtener todas las cuentas
    @GetMapping(value="/all")
    public List<Movement> getAll(){
        List<Movement> movements = movementService.getAll();
        return movements;
    }

    //Seleccionar una cuenta
    @GetMapping(value="/find/{id}")
    public Movement find(@PathVariable String id){
        return movementService.get(id);
    }


    @PostMapping(value="/save/{id}")
    public ResponseEntity<String> save(@PathVariable String id, @RequestParam ("place") String place){

        Movement movement = movementService.save(place);
        accountService.saveMovement(id,movement);

        String info = "Movimiento creado";

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

}
