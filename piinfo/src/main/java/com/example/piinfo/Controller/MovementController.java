package com.example.piinfo.Controller;


import com.example.piinfo.model.Movement;
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

    public MovementController(MovementService movementService) {
        this.movementService = movementService;
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

    //Guardar cuenta (no es parte del sistema, solo para probar)
    @PostMapping(value="/save")
    public ResponseEntity<Movement> save(@RequestBody Movement movement){
        Movement obj = movementService.save(movement);
        return new ResponseEntity<Movement>(obj, HttpStatus.OK);
    }

}
