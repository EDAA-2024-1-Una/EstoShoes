package com.EstoShoes.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EstoShoes.api.dto.VendaDTO;
import com.EstoShoes.api.service.VendaService;

@RestController
@RequestMapping("vendas")
public class vendaController {
    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<VendaDTO> criarVenda(@RequestBody VendaDTO vendaDTO) {
        VendaDTO createdVenda = vendaService.criar(vendaDTO);
        return ResponseEntity.ok(createdVenda);
    }

    @GetMapping
    public ResponseEntity<List<VendaDTO>> getAllVendas() {
        List<VendaDTO> vendas = vendaService.listar();
        return ResponseEntity.ok(vendas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaDTO> getVendaById(@PathVariable Integer id) {
        VendaDTO venda = vendaService.getVendaPorId(id);
        return ResponseEntity.ok(venda);
    }
}
