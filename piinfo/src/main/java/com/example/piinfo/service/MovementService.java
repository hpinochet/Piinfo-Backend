package com.example.piinfo.service;

import com.example.piinfo.model.Movement;
import com.example.piinfo.repository.MovementRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class MovementService {

    private final MovementRepository movementRepository;

    public MovementService(MovementRepository movementRepository){
        this.movementRepository = movementRepository;
    }

    // Listado de cuentas
    public List<Movement> getAll(){
        List<Movement> movements = movementRepository.findAll();
        return movements;
    }

    // Obtener cuenta
    public Movement get(String id){
        Optional<Movement> obj = movementRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Movement save(String place){

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

        Movement movement = new Movement(place,date,time);
        Movement newMovement = movementRepository.save(movement);

        return newMovement;
    }

    public String dayTimeVerify(String day) {

        if(day.length() == 1){
            day = "0" + day;
        }
        return day;
    }

}