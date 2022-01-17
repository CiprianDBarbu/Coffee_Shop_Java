package com.example.coffees.service;

import com.example.coffees.model.Syrup;
import com.example.coffees.repository.SyrupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SyrupServiceTest {

    @InjectMocks
    private SyrupService syrupService;

    @Mock
    private SyrupRepository syrupRepository;

    @Test
    @DisplayName("Saving new syrup in a happy flow")
    void saveNewSyrupHappyFlow() {
        //arrange
        Syrup syrup = new Syrup("SyrupTest", 0.2);
        when(syrupRepository.save(syrup)).thenReturn(syrup);
        //act
        Syrup result = syrupService.saveNewSyrup(syrup);
        //assert
        assertNotNull(result);
        assertEquals(syrup.getSyrupId(), result.getSyrupId());
        assertEquals(syrup.getName(), result.getName());
        assertEquals(syrup.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Retrieving syrups in a happy flow")
    void retrieveCoffeesHappyFlow() {
        //arrange
        List<Syrup> syrupList = new ArrayList<>();
        Syrup syrup = new Syrup("SyrupTest", 0.2);
        syrupList.add(syrup);
        when(syrupRepository.findAll()).thenReturn(syrupList);
        //act
        List<Syrup> syrupListResult =  syrupService.retrieveSyrups();
        //assert
        assertNotNull(syrupListResult);
        assertEquals(1, syrupListResult.size());
        assertEquals(syrupList, syrupListResult);
    }

    @Test
    @DisplayName("Editing syrup in a happy flow")
    void editSyrupHappyFlow() {
        //arrange
        Syrup syrupOld = new Syrup("SyrupTestOld", 0.2);
        Syrup syrupNew = new Syrup("SyrupTestNew", 1.2);
        when(syrupRepository.findById(syrupOld.getSyrupId())).thenReturn(Optional.of(syrupOld));
        when(syrupRepository.save(syrupOld)).thenReturn(syrupNew);
        //act
        Syrup result = syrupService.editSyrup(syrupOld.getSyrupId(), syrupNew);
        //assert
        assertNotNull(result);
        assertEquals(syrupNew.getSyrupId(), result.getSyrupId());
        assertEquals(syrupNew.getName(), result.getName());
        assertEquals(syrupNew.getPrice(), result.getPrice());
    }

    @Test
    @DisplayName("Deleting syrup in a happy flow")
    void deleteSyrupHappyFlow() {
        //arrange
        String deletedSyrup = "OK!";
        //act
        String result = syrupService.deleteSyrupById(0);
        //assert
        assertEquals(deletedSyrup, result);
        verify(syrupRepository).deleteById(0);
    }

}
