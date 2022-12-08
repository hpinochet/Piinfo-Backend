package com.example.piinfo.Controller;

import com.example.piinfo.model.Account;
import com.example.piinfo.service.AccountService;
import com.example.piinfo.service.Product_UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("account")
public class AccountController {

    private final AccountService accountService;
    private final Product_UserService product_userService;

    public AccountController(AccountService accountService , Product_UserService product_userService) {
        this.accountService = accountService;
        this.product_userService = product_userService;
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
    public ResponseEntity<String> save(@RequestBody Account account){

        String info = product_userService.verifyProduct(account.getProduct_number());

        if(info.equals("No existe el producto")){
            return new ResponseEntity<String>("El codigo de producto ingresado no existe", HttpStatus.OK);
        }

        Account obj = accountService.save(account);

        product_userService.registerUserProduct(info, obj.getId());

        return new ResponseEntity<String>("La cuenta a sido registrada correctamente", HttpStatus.OK);
    }

    // Iniciar sesion
    @GetMapping(value="/login")
    public ResponseEntity<String> login(@RequestParam ("email") String email,
                                        @RequestParam ("password") String password){

        String info = accountService.login(email,password);

        return new ResponseEntity<String>(info, HttpStatus.OK);

    }

}
