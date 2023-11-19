package com.teste.crud.service;

import com.teste.crud.entity.Client;
import com.teste.crud.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository repository;

    @Autowired
    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Client findClientById(Long id) {
        return repository.findOneById(id);
    }

    @Override
    public void updateClient(Client client) {
        repository.save(client);
    }
}
