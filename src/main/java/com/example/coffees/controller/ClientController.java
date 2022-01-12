package com.example.coffees.controller;

import com.example.coffees.model.Client;
import com.example.coffees.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> retrieveClients() {
        return ResponseEntity.ok()
                .body(clientService.retrieveClients());
    }

    @PostMapping("/new")
    public ResponseEntity<Client> saveNewClient(@RequestBody Client client) {
        return ResponseEntity.ok()
                .body(clientService.saveNewClient(client));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClientById(@PathVariable int clientId) {
        return ResponseEntity.ok()
                .body(clientService.deleteClientById(clientId));
    }
}
