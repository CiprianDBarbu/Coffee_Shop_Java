package com.example.coffees.service;

import com.example.coffees.model.Client;
import com.example.coffees.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client saveNewClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> retrieveClients() {
        return clientRepository.findAll();
    }

    public String deleteClientById(int clientId) {
        clientRepository.deleteById(clientId);
        return "OK!";
    }
}
