package com.proyecto.rutas.services.programacion.inf;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.proyecto.rutas.model.security.Usuario;
import com.proyecto.rutas.services.generic.GenericService;

public interface UsuarioService extends GenericService<Usuario>{
	
	public Usuario findByUsername(String username) throws UsernameNotFoundException;
	
	List<Usuario> findAllUsuarios();


}
