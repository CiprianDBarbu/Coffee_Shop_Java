package com.example.coffees.service;

import com.example.coffees.exceptions.NoElementFoundException;
import com.example.coffees.model.Syrup;
import com.example.coffees.repository.SyrupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SyrupService {
    private final SyrupRepository syrupRepository;

    public SyrupService(SyrupRepository syrupRepository) {
        this.syrupRepository = syrupRepository;
    }

    public Syrup saveNewSyrup(Syrup syrup) {
        return syrupRepository.save(syrup);
    }

    public List<Syrup> retrieveSyrups() {
        return syrupRepository.findAll();
    }

    public String deleteSyrupById(int syrupId) {
        syrupRepository.deleteById(syrupId);
        return "OK!";
    }

    public Syrup editSyrup(int syrupId, Syrup syrup) {
        Syrup actualSyrup = syrupRepository.findById(syrupId)
                .orElseThrow(() -> new NoElementFoundException("Id not available"));

        actualSyrup.setName(syrup.getName());
        actualSyrup.setPrice(syrup.getPrice());

        return syrupRepository.save(actualSyrup);
    }
}
