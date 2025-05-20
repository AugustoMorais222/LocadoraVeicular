package com.senai.aluguel_veiculos_api.dtos;

import java.time.LocalDate;

public class AluguelDto {
	private Long id;
    private Long clienteId;
    private Long veiculoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    
	public AluguelDto() {
	}
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getVeiculoId() {
		return veiculoId;
	}
	public void setVeiculoId(Long veiculoId) {
		this.veiculoId = veiculoId;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
    
    
    
    
}