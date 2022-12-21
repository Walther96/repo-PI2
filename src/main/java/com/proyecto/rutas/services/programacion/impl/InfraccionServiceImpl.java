package com.proyecto.rutas.services.programacion.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.InfraccionEntity;
import com.proyecto.rutas.repository.InfraccionRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.InfraccionService;

@Service
public class InfraccionServiceImpl implements InfraccionService{

	@Autowired
	private InfraccionRepository infraccionrepository;
	
	
	
	@Override
	public InfraccionEntity findById(Long id) throws ServiceException {

		return infraccionrepository.findById(id).orElse(null);
	}

	@Override
	public List<InfraccionEntity> getAll() throws ServiceException {
		return infraccionrepository.findAllInfracciones();
	}

	@Override
	public InfraccionEntity save(InfraccionEntity infraccion) throws ServiceException {
		if (infraccion.getId()>0) {
			InfraccionEntity oInfraccion= this.findById(infraccion.getId());
			BeanUtils.copyProperties(infraccion, oInfraccion);

		}
		
		return infraccionrepository.save(infraccion);
	}

	@Override
	public InfraccionEntity delete(Long id) throws ServiceException {
		InfraccionEntity oInfraccion= this.findById(id);
		oInfraccion.setEstado("0");
		return infraccionrepository.save(oInfraccion);
	}

}
