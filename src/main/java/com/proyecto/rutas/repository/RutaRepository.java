package com.proyecto.rutas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto.rutas.model.Entity.RutaEntity;

@Repository
public interface RutaRepository extends JpaRepository<RutaEntity, Long> {

	@Query("select t from Ruta t where t.estado='1'")
	List<RutaEntity> findAllRuta();

	
	@Query("select t from Ruta t where t.nombreruta=:nombre")
	RutaEntity FindRutaXNombre(@Param("nombre") String nombre);
}
