package com.example.bookservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookservice.model.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

}
