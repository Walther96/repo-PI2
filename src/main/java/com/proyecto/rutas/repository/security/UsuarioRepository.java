package com.proyecto.rutas.repository.security;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.rutas.model.security.Usuario;
import com.proyecto.rutas.repository.IGenericRepository;


@Repository
public interface UsuarioRepository extends IGenericRepository<Usuario, Long> {

	@Query("select t from Usuario t where t.usuario=:usuario and t.estado='1'")
	List<Usuario> findByUsuario(@Param("usuario")String usuario);
	
	@Query("select t from Usuario t where t.usuario=:usuario and t.estado='1'")
	Usuario loadUserByUsername(@Param("usuario")String usuario);
	
}
