package com.senai.aluguel_veiculos_api.model;

import jakarta.persistence.Entity;

@Entity
public class Moto extends Veiculo {
    @Override
    public TipoVeiculo getTipo() {
        return TipoVeiculo.MOTO;
    }
}
