package com.example.piinfo.Controller;

import com.example.piinfo.model.Movement;
import com.example.piinfo.model.Product_User;
import com.example.piinfo.service.AccountService;
import com.example.piinfo.service.MovementService;
import com.example.piinfo.service.Product_UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("product_user")
public class Product_UserController {

    private final Product_UserService product_userService;

    public Product_UserController(Product_UserService product_userService) {
        this.product_userService = product_userService;
    }
    @PostMapping(value="/save")
    public ResponseEntity<String> save(@RequestParam ("product_number") String product_number){

        product_userService.save(product_number);
        String info = "Producto registrado";

        return new ResponseEntity<String>(info, HttpStatus.OK);
    }

    @GetMapping(value="/sync")
    public ResponseEntity<String> login(@RequestParam ("product_number") String product_number){
        String info = product_userService.sync(product_number);
        return new ResponseEntity<String>(info, HttpStatus.OK);

    }

}
