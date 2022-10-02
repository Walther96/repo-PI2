package com.proyecto.rutas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.rutas.model.Entity.DestinoEntity;

@Repository
public interface DestinoRepository extends JpaRepository<DestinoEntity, Long>  {

	@Query("select t from Destino t where t.estado='1'")
	List<DestinoEntity> findAllDestino();

}
