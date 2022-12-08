package com.example.piinfo.Controller;

import com.example.piinfo.model.Account;
import com.example.piinfo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Obtener todas las cuentas
    @GetMapping(value="/all")
    public List<Account> getAll(){
        List<Account> accounts = accountService.getAll();
        return accounts;
    }

    //Seleccionar una cuenta
    @GetMapping(value="/find/{id}")
    public Account find(@PathVariable String id){
        return accountService.get(id);
    }

    //Guardar cuenta (no es parte del sistema, solo para probar)
    @PostMapping(value="/save")
    public ResponseEntity<Account> save(@RequestBody Account account){
        Account obj = accountService.save(account);
        return new ResponseEntity<Account>(obj, HttpStatus.OK);
    }

    // Iniciar sesion
    @GetMapping(value="/login")
    public ResponseEntity<String> login(@RequestParam ("email") String email,
                                        @RequestParam ("password") String password){

        String info = accountService.login(email,password);

        return new ResponseEntity<String>(info, HttpStatus.OK);

    }

}
