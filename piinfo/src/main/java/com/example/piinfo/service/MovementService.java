package com.example.piinfo.service;

import com.example.piinfo.model.Movement;
import com.example.piinfo.repository.MovementRepository;
import org.springframework.stereotype.Service;

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

    // Crear cuenta (solo para prueba, no es parte del sistema)
    public Movement save(Movement entity){
        Movement newMovement = movementRepository.save(entity);
        return newMovement;
    }

    // Obtener cuenta
    public Movement get(String id){
        Optional<Movement> obj = movementRepository.findById(id);
        if(obj.isPresent()){
            return obj.get();
        }
        return null;
    }

}