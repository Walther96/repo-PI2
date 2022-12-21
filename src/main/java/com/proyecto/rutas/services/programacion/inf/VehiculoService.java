package com.proyecto.rutas.services.programacion.inf;


import com.proyecto.rutas.model.Entity.VehiculoEntity;
import com.proyecto.rutas.services.generic.GenericService;

public interface VehiculoService extends GenericService<VehiculoEntity> {
	
	VehiculoEntity FindXPlaca(String placa);

}
