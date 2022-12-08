package com.example.piinfo.service;

import com.example.piinfo.model.*;
import com.example.piinfo.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    public void saveMovement(String id, Movement movement) {

        Optional<Account> obj = accountRepository.findById(id);
        if(obj.isPresent()) {
            Account account = obj.get();
            List<Movement> movements = account.getMovements();
            movements.add(movement);
            account.setMovements(movements);
            accountRepository.save(account);
        }

    }

    public void saveAlert(String id, Alert alert) {

        Optional<Account> obj = accountRepository.findById(id);
        if(obj.isPresent()) {
            Account account = obj.get();
            List<Alert> alerts = account.getAlerts();
            alerts.add(alert);
            account.setAlerts(alerts);
            accountRepository.save(account);
        }

    }

    public void saveConversation(String id, Conversation conversation) {

        Optional<Account> obj = accountRepository.findById(id);
        if(obj.isPresent()) {
            Account account = obj.get();
            List<Conversation> conversations = account.getConversations();
            conversations.add(conversation);
            account.setConversations(conversations);
            accountRepository.save(account);
        }

    }


    public String login(String email, String password){

        List<Account> accounts = accountRepository.findAll();

        int userFlag = 0;
        int passwordFlag = 0;
        String Id = "";

        for (Account a : accounts) {
            if(a.getEmail().equals(email)){
                userFlag = 1;
                if(a.getPassword().equals(password)){
                    passwordFlag = 1;
                    Id = a.getId();
                }
            }
        }

        if(userFlag == 0){
            return "No existe una cuenta con este RUT";
        }
        if(passwordFlag == 0){
            return"La contraseña de la cuenta es incorrecta";
        }
        return Id;

    }

    public String numberCell(String id){

        String numberCell = "";

        Optional<Account> obj = accountRepository.findById(id);
        if(obj.isPresent()) {
            Account account = obj.get();
            numberCell = account.getPhone_number();
        }


        return numberCell;

    }

    public String dayTimeVerify(String day) {

        if(day.length() == 1){
            day = "0" + day;
        }
        return day;
    }


    public List<Conversation> getAllUserConversation(String id) {

        Optional<Account> obj = accountRepository.findById(id);
        List<Conversation> userConversation = new ArrayList<Conversation>();

        if(obj.isPresent()) {
            Account account = obj.get();
            userConversation = account.getConversations();
        }

        return userConversation;

    }

    public List<Movement> getAllUserMovement(String id) {

        Optional<Account> obj = accountRepository.findById(id);
        List<Movement> userMovement = new ArrayList<Movement>();

        if(obj.isPresent()) {
            Account account = obj.get();
            userMovement = account.getMovements();
        }

        return userMovement;

    }

    public List<Reminder> getAllUserReminder(String id) {

        Optional<Account> obj = accountRepository.findById(id);
        List<Reminder> userReminder = new ArrayList<Reminder>();

        if(obj.isPresent()) {
            Account account = obj.get();
            userReminder = account.getReminders();
        }

        return userReminder;

    }

    public List<Alert> getAllUserAlert(String id) {

        Optional<Account> obj = accountRepository.findById(id);
        List<Alert> userAlert = new ArrayList<Alert>();

        if(obj.isPresent()) {
            Account account = obj.get();
            userAlert = account.getAlerts();
        }

        return userAlert;
    }
}
