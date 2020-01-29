package com.makingapp.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.makingapp.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long> {
	
	public Usuario findByUsername(String username);
	
	public Usuario findByUsernameAndPassword(String username, String password);
	
	//@Query("select u from Usuario u where u.username=?1 and u.otro=?2")
	//public Usuario findByUsername2(String username);

}
