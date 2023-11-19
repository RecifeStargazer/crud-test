package com.teste.crud.repository;

import com.teste.crud.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
      Client findOneById(Long id);
}
