package com.example.piinfo.Controller;

import com.example.piinfo.model.Alert;
import com.example.piinfo.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("alert")
public class AlertController {

    private final AlertService alertService;

    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    //Obtener todas las cuentas
    @GetMapping(value="/all")
    public List<Alert> getAll(){
        List<Alert> alerts = alertService.getAll();
        return alerts;
    }

    //Seleccionar una cuenta
    @GetMapping(value="/find/{id}")
    public Alert find(@PathVariable String id){
        return alertService.get(id);
    }

    //Guardar cuenta (no es parte del sistema, solo para probar)
    @PostMapping(value="/save/{id}")
    public ResponseEntity<Alert> save(@PathVariable String id, @RequestBody Alert alert){
        Alert obj = alertService.save(alert);
        return new ResponseEntity<Alert>(obj, HttpStatus.OK);
    }

}

