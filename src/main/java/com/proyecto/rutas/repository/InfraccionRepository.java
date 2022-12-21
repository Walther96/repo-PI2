package com.proyecto.rutas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.proyecto.rutas.model.Entity.InfraccionEntity;

public interface InfraccionRepository extends IGenericRepository<InfraccionEntity, Long> {

	@Query("select t from Infraccion t where t.estado='1'")
	List<InfraccionEntity> findAllInfracciones();
}
