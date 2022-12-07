package com.example.piinfo.service;

import com.example.piinfo.model.Account;
import com.example.piinfo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    // Listado de cuentas
    public List<Account> getAll(){
        List<Account> Accounts = accountRepository.findAll();
        return Accounts;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Account save(Account entity){
        Account nuevFactura = accountRepository.save(entity);
        return nuevFactura;
    }

    // Obtener cuenta
    public Account get(String id){
        Optional<Account> obj = accountRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

}
