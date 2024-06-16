package com.EstoShoes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EstoShoes.api.entity.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

}
