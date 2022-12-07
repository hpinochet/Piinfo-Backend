package com.example.piinfo.service;

import com.example.piinfo.model.Account;
import com.example.piinfo.model.Reminder;
import com.example.piinfo.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void saveReminder(String id, Reminder reminder){
        Optional<Account> obj = accountRepository.findById(id);
        if(obj.isPresent()) {
            Account account = obj.get();
            List<Reminder> reminders = account.getReminders();
            reminders.add(reminder);
            account.setReminders(reminders);
            accountRepository.save(account);
        }
    }

    public String doReminder(String id){

        String info = "No hay recordatorio activo";

        Optional<Account> obj = accountRepository.findById(id);
        if(!obj.isPresent()) {
            return "No existe este usuario";
        }
        Account account = obj.get();

        List<Reminder> reminders = account.getReminders();

        Calendar c1 = Calendar.getInstance();

        String actualDay = Integer.toString(c1.get(Calendar.DATE));
        String actualMonth = Integer.toString(c1.get(Calendar.MONTH) + 1);
        String actualYear = Integer.toString(c1.get(Calendar.YEAR));

        String actualHour = Integer.toString(c1.get(Calendar.HOUR_OF_DAY));
        String actualMinute = Integer.toString(c1.get(Calendar.MINUTE));

        actualDay = dayTimeVerify(actualDay);
        actualMonth = dayTimeVerify(actualMonth);
        actualHour = dayTimeVerify(actualHour);
        actualMinute = dayTimeVerify(actualMinute);

        String date = actualDay + "/" + actualMonth + "/" + actualYear;
        String time = actualHour + ":" + actualMinute;
        
        List<Reminder> newReminders = new ArrayList<Reminder>();

        for (Reminder r : reminders) {
            if(r.getDate().equals(date) && r.getTime().equals(time) && r.getDone() == 0){
                r.setDone(1);
                newReminders.add(r);
                info = r.getId();
            }else{
                newReminders.add(r);
            }
        }

        account.setReminders(newReminders);
        accountRepository.save(account);

        return info;

    }




    public String dayTimeVerify(String day) {

        if(day.length() == 1){
            day = "0" + day;
        }
        return day;
    }

}
