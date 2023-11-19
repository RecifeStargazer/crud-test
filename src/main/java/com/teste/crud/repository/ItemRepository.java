package com.teste.crud.repository;

import com.teste.crud.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByControlNumber(Long controlNumber);
    List<Item> findByRegistrationDate(LocalDate date);
}
