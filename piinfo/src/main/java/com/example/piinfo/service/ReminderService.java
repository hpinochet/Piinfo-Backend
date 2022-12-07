package com.example.piinfo.service;


import com.example.piinfo.model.Account;
import com.example.piinfo.model.Movement;
import com.example.piinfo.model.Reminder;
import com.example.piinfo.repository.AccountRepository;
import com.example.piinfo.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;
    private final AccountRepository accountRepository;

    public ReminderService(ReminderRepository reminderRepository, AccountRepository accountRepository) {
        this.reminderRepository = reminderRepository;
        this.accountRepository = accountRepository;
    }

    // Listado de cuentas
    public List<Reminder> getAll() {
        List<Reminder> reminders = reminderRepository.findAll();
        return reminders;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public String verify(String date) {

        // Ejemplo "el 10 de febrero del 2022 a las 23 5"

        // Split
        String[] words = date.split(" ");

        System.out.println(words.length);

        // Structure verify
        if(words.length != 10){
            return "Indique nuevamente el recordatorio con la estrucutura correcta";
        }
        if(!words[0].equals("el") && !words[2].equals("de") && !words[4].equals("del") && !words[6].equals("a")
                && !words[7].equals("las")){
            return "Indique nuevamente el recordatorio con la estrucutura correcta";
        }

        //SimpleDateFormat formatter3 = new SimpleDateFormat("hh:mm");
        //Date out_hour = formatter3.parse("18:00");
        //SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        //Date date1 = formatter1.parse(justificativo.getFecha());

        // Date process

        String day = dayTimeVerify(words[1]);
        String month = monthVerify(words[3]);
        String year = words[5];

        String hour = dayTimeVerify(words[8]);
        String minute = dayTimeVerify(words[9]);

        String date2 = day + "/" + month + "/" + year;
        String time = hour + minute;

        Boolean res = validarFecha(date2);
        if(!res) {
            return "Indique una fecha valida en el recordatorio";
        }

        return "El recordatorio esta estructurado";
    }

    public Reminder save(String description, String date){

        // Date extraction
        String[] words = date.split(" ");

        String day = dayTimeVerify(words[1]);
        String month = monthVerify(words[3]);
        String year = words[5];

        String hour = dayTimeVerify(words[8]);
        String minute = dayTimeVerify(words[9]);

        String date2 = day + "/" + month + "/" + year;
        String time = hour + ":" + minute;

        Reminder reminder = new Reminder(description, date2, time);
        reminder = reminderRepository.save(reminder);

        return reminder;
    }

    public static boolean validarFecha(String fecha) {
        try {
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
            formatoFecha.setLenient(false);
            formatoFecha.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public String dayTimeVerify(String day) {

        if(day.equals("1")){
            day = "01";
        }
        if(day.equals("2")){
            day = "02";
        }
        if(day.equals("3")){
            day = "03";
        }
        if(day.equals("4")){
            day = "04";
        }
        if(day.equals("5")){
            day = "05";
        }
        if(day.equals("6")){
            day = "06";
        }
        if(day.equals("7")){
            day = "07";
        }
        if(day.equals("8")){
            day = "08";
        }
        if(day.equals("9")){
            day = "09";
        }
        return day;
    }

    public String monthVerify(String month) {

        if(month.equals("enero")){
            month = "01";
        }
        if(month.equals("febrero")){
            month = "02";
        }
        if(month.equals("marzo")){
            month = "03";
        }
        if(month.equals("abril")){
            month = "04";
        }
        if(month.equals("mayo")){
            month = "05";
        }
        if(month.equals("junio")){
            month = "06";
        }
        if(month.equals("julio")){
            month = "07";
        }
        if(month.equals("agosto")){
            month = "08";
        }
        if(month.equals("septiembre")){
            month = "09";
        }
        if(month.equals("octubre")){
            month = "10";
        }
        if(month.equals("noviembre")){
            month = "11";
        }
        if(month.equals("diciembre")){
            month = "12";
        }

        return month;
    }

    // Obtener cuenta
    public Reminder get(String id){
        Optional<Reminder> obj = reminderRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

}