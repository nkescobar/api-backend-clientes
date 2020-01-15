package com.makingapp.springboot.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.makingapp.springboot.backend.apirest.models.entity.Cliente;
import com.makingapp.springboot.backend.apirest.models.services.IClienteService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return clienteService.findAll();
	}
	
	@GetMapping("/clientes/page/{page}/size/{size}")
	public Page<Cliente> index(@PathVariable Integer page, @PathVariable Integer size){
		Pageable pageable = PageRequest.of(page, size);
		return clienteService.findAll(pageable);
	}
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;		
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteService.findById(id);

		} catch (DataAccessException e) {
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("mensaje", "Error al realizar la consulta en la base de datos.");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (cliente == null) {
			response.put("codigo", HttpStatus.NOT_FOUND);
			response.put("mensaje", "El cliende ID: ".concat(id.toString()
					.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		response.put("codigo", HttpStatus.OK);
		response.put("mensaje", "");
		response.put("response", cliente);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		//cliente.setCreateAt(new Date());
		
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			/*List<String> errors = new ArrayList<>();
			
			for(FieldError err: result.getFieldErrors()) {
				errors.add("Campo : " + err.getField() + " - "+  err.getDefaultMessage());
			}*/
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "Campo : " + err.getField() + " - "+  err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			response.put("codigo", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("mensaje", "Error al crear el usuario en la base de datos.");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	
		}
		response.put("codigo", HttpStatus.CREATED);
		response.put("mensaje", "El cliente ha sido creado con exito!.");
		response.put("response", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdated = null;
		
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "Campo : " + err.getField() + " - "+  err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			response.put("codigo", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

		}

		if (clienteActual == null) {
			response.put("codigo", HttpStatus.NOT_FOUND);
			response.put("mensaje", "Error: no se pudo editar, el cliente ID:  ".concat(id.toString()
					.concat(" no existe en la base de datos.")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setEmail(cliente.getEmail());
			//clienteActual.setCreateAt(cliente.getCreateAt());
			clienteUpdated = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			// TODO: handle exception
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("codigo", HttpStatus.CREATED);
		response.put("mensaje", "El cliente ha sido actualizado con exito!.");
		response.put("response", clienteUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}
	
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();

		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR);
			response.put("mensaje", "Error al eliminar el usuario en la base de datos.");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("codigo", HttpStatus.OK);
		response.put("mensaje", "El cliente ha sido eliminado con exito!.");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
}
