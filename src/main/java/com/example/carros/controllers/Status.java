package com.example.carros.controllers;

public enum Status {

    ATIVO("ativo"),
	DESATIVADO("desativado"),
	MANUTENÇÃO("manutenção");
	
	private String status;

	Status(String status) {
		this.status=status;
	}
	
	public String getStatus() {
		return status;
	}

}