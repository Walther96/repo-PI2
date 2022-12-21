package com.proyecto.rutas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto.rutas.model.Entity.GrupoInspectoresEntity;

public interface GrupoInspectoresRepository extends IGenericRepository<GrupoInspectoresEntity, Long>{

	
	@Query("select t from GrupoInspectores t where t.estado='1'")
	List<GrupoInspectoresEntity> findAllGrupoInspectores();

	
	@Query("select t from GrupoInspectores t where t.codigo=:codigo")
	GrupoInspectoresEntity FindXCodigo(@Param("codigo") String codigo);
	
	@Query("select t from GrupoInspectores t where t.codigo=:distrito")
	GrupoInspectoresEntity FindXDistrito(@Param("distrito") String distrito);
}
