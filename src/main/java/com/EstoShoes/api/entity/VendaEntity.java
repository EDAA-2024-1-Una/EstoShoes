package com.EstoShoes.api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private UsuarioEntity usuario;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private ClienteEntity cliente;

    private BigDecimal valorTotal;
    private int quantidadeProdutos;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL)
    private List<ItemVendaEntity> itensVenda;

    public void setItensVenda(List<ItemVendaEntity> itensVenda) {
        this.itensVenda = itensVenda;
        if (itensVenda != null) {
            for (ItemVendaEntity item : itensVenda) {
                item.setVenda(this);
            }
        }
    }
}
