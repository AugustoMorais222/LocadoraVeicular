package com.senai.aluguel_veiculos_api.model;

import jakarta.persistence.Entity;

@Entity
public class Carro extends Veiculo {
    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.CARRO;
    }
}
