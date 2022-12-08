package com.example.piinfo.Controller;

import com.example.piinfo.model.Alert;
import com.example.piinfo.model.Movement;
import com.example.piinfo.service.AccountService;
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
    private final AccountService accountService;

    public AlertController(AlertService alertService, AccountService accountService) {
        this.alertService = alertService;
        this.accountService = accountService;
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

    @PostMapping(value="/save/{id}")
    public ResponseEntity<String> save(@PathVariable String id,
                                       @RequestParam ("alert_description") String alert_description){

        Alert alert = alertService.save(alert_description);
        accountService.saveAlert(id,alert);

        String numberCell = accountService.numberCell(id);
        alertService.sendAlert(numberCell, alert_description);

        String info = "Alerta creada";

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }




}

