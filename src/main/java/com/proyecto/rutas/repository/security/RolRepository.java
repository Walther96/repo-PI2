package com.proyecto.rutas.repository.security;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.proyecto.rutas.model.security.Rol;
import com.proyecto.rutas.repository.IGenericRepository;

public interface RolRepository extends IGenericRepository<Rol, Long>{
	@Query("select t from Rol t where t.estado='1'")
	List<Rol> findAllRol();
}
