package com.teste.crud.service;

import com.teste.crud.entity.Client;

public interface ClientService {

    public Client findClientById(Long id);

    public void updateClient(Client client);
}
