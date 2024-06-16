package com.EstoShoes.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EstoShoes.api.entity.ItemVenda;

@Repository
public interface ItemVendaRepository extends JpaRepository<ItemVenda, Integer> {

}
