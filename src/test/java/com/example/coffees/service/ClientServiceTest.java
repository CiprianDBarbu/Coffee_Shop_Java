package com.example.coffees.service;

import com.example.coffees.model.Client;
import com.example.coffees.repository.ClientRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    @DisplayName("Saving new client in a happy flow")
    void saveNewClientHappyFlow() {
        //arrange
        Client client = new Client("ClientTest");
        when(clientRepository.save(client)).thenReturn(client);
        //act
        Client result = clientService.saveNewClient(client);
        //assert
        assertNotNull(result);
        assertEquals(client.getClientId(), result.getClientId());
        assertEquals(client.getName(), result.getName());
    }

    @Test
    @DisplayName("Retrieving clients in a happy flow")
    void retrieveClientsHappyFlow() {
        //arrange
        List<Client> clientList = new ArrayList<>();
        Client client = new Client("ClientTest");
        clientList.add(client);
        when(clientRepository.findAll()).thenReturn(clientList);
        //act
        List<Client> clientListResult = clientService.retrieveClients();
        //assert
        assertNotNull(clientListResult);
        assertEquals(1, clientListResult.size());
        assertEquals(clientList, clientListResult);
    }

    @Test
    @DisplayName("Deleting client in a happy flow")
    void deleteClientHappyFlow() {
        //arrange
        String deletedClient = "OK!";
        //act
        String result = clientService.deleteClientById(0);
        //assert
        assertEquals(deletedClient, result);
        verify(clientRepository).deleteById(0);
    }

}
