package com.makingapp.springboot.backend.apirest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.makingapp.springboot.backend.apirest.models.entity.Factura;
import com.makingapp.springboot.backend.apirest.models.services.IFacturaService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class FacturaRestController {
	
	@Autowired
	private IFacturaService facturaService;
	
	@GetMapping("/facturas/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Factura show(@PathVariable Long id) {
		return facturaService.findFacturaById(id);
	}

}
