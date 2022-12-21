package com.proyecto.rutas.services.security.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.model.security.Rol;
import com.proyecto.rutas.repository.security.RolRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.security.inf.RolService;

@Service
public class RolServiceImpl implements RolService{
	
	@Autowired
	private RolRepository rolRepository;
	@Override
	public DestinoEntity findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DestinoEntity> getAll() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DestinoEntity save(DestinoEntity t) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DestinoEntity delete(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Rol> findAllRol() {
		// TODO Auto-generated method stub
		return rolRepository.findAllRol();
	}

}
