package com.proyecto.rutas.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.proyecto.rutas.model.Entity.PapeletaEntity;

public interface PapeletaRepository extends IGenericRepository<PapeletaEntity, Long>{

	@Query("select t from Papeleta t where t.estado='1'")
	List<PapeletaEntity> findAllPapeletas();
	
	@Query("select rs from Papeleta rs where rs.estado='1' and rs.fecha between :desde and :hasta")
	public List<PapeletaEntity> findByFecha(Date desde, Date hasta);
}
