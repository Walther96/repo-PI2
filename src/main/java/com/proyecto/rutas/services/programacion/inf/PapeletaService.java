package com.proyecto.rutas.services.programacion.inf;

import java.util.Date;
import java.util.List;

import com.proyecto.rutas.model.Entity.PapeletaEntity;
import com.proyecto.rutas.model.Entity.PapeletaRequest;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.generic.GenericService;

public interface PapeletaService extends GenericService<PapeletaEntity>{

	
	PapeletaEntity savePapeleta(PapeletaRequest request) throws ServiceException;

	List<PapeletaEntity> findByFecha(Date desde, Date hasta) throws ServiceException;

}
