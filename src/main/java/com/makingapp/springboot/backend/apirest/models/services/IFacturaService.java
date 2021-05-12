package com.makingapp.springboot.backend.apirest.models.services;

import com.makingapp.springboot.backend.apirest.models.entity.Factura;

public interface IFacturaService {

	public Factura findFacturaById(Long id);
	
	public Factura saveFctura(Factura factura);

	public void deleteFacturaById(Long id);
}
