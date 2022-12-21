package com.proyecto.rutas.services.security.inf;

import java.util.List;

import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.model.security.Rol;
import com.proyecto.rutas.services.generic.GenericService;

public interface RolService extends GenericService<DestinoEntity>{

	List<Rol> findAllRol();

}
