package com.proyecto.rutas.services.programacion.inf;



import com.proyecto.rutas.model.Entity.RutaEntity;
import com.proyecto.rutas.services.generic.GenericService;

public interface RutaService extends GenericService<RutaEntity> {
	
	RutaEntity FindRutaXNombre(String nombre);


}
