package com.example.piinfo.service;

import com.example.piinfo.model.Alert;
import com.example.piinfo.model.Movement;
import com.example.piinfo.repository.AlertRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    private final AlertRepository alertRepository;

    public AlertService(AlertRepository alertRepository){
        this.alertRepository = alertRepository;
    }

    // Listado de cuentas
    public List<Alert> getAll(){
        List<Alert> alerts = alertRepository.findAll();
        return alerts;
    }

    // Obtener cuenta
    public Alert get(String id){
        Optional<Alert> obj = alertRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Alert save(String alert_description){

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

        Alert alert = new Alert(alert_description, date, time);
        Alert newAlert = alertRepository.save(alert);

        return newAlert;
    }

    public String dayTimeVerify(String day) {

        if(day.length() == 1){
            day = "0" + day;
        }
        return day;
    }

    public void sendAlert(String numberCell, String alert_description) {

        String ACCOUNT_SID = "ACa389319651793e5c07eb6b3998fdd924";
        String AUTH_TOKEN = "6d3cd15ab23ba721b1389e62be102cc1";

        String numberCellMessage = "whatsapp:" + numberCell;
        String botMessage = "ALERTA: Se ha detectado una alerta de tipo " + alert_description + " en el hogar";


        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(numberCellMessage),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                            botMessage)
                    .create();


    }
}