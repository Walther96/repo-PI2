package com.proyecto.rutas.services.generic;

import java.util.List;

import com.proyecto.rutas.services.Exception.ServiceException;

public interface GenericService<T> {

	T findById(Long id) throws ServiceException;
	
	List<T> getAll() throws ServiceException;
	
	T save(T t) throws ServiceException;

	T delete(Long id)throws ServiceException;
}
