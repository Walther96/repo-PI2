package com.proyecto.rutas.services.programacion.inf;

import com.proyecto.rutas.model.Entity.GrupoInspectoresEntity;
import com.proyecto.rutas.services.generic.GenericService;

public interface GrupoInspectoresService extends GenericService<GrupoInspectoresEntity>{

	
	GrupoInspectoresEntity FindXCodigo(String codigo);
	
	GrupoInspectoresEntity FindXDistrito(String distrito);


}
