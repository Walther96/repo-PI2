package com.proyecto.rutas.controller.programacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.rutas.controller.commons.ResponseREST;
import com.proyecto.rutas.controller.generic.GenericController;
import com.proyecto.rutas.model.security.Rol;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.security.inf.RolService;
import com.proyecto.rutas.services.Exception.ServiceException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/rol/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class RolController extends GenericController{

	@Autowired
	private RolService rolService;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getOrigenes() throws ServiceException {
		List<Rol> lst=this.rolService.findAllRol();
		if (lst.isEmpty()) {
			return super.getNotFoundRequest();
		}
		return super.getOKConsultaRequest(lst);
	}
}
