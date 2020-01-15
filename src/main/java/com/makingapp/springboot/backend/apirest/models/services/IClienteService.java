package com.makingapp.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.makingapp.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {

	public Page<Cliente> findAll(Pageable pageable);

	public List<Cliente> findAll();
	
	public Cliente findById(Long id);
	
	public Cliente save(Cliente cliente);
	
	public void delete(Long id);
	
}
