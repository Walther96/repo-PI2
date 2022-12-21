package com.proyecto.rutas.controller.programacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.rutas.controller.commons.ResponseREST;
import com.proyecto.rutas.controller.generic.GenericController;
import com.proyecto.rutas.model.Entity.InfraccionEntity;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.InfraccionService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/infraccion/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class InfraccionController extends GenericController{

	@Autowired
	private InfraccionService infraccionService;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getInfracciones() {
		try {
			List<InfraccionEntity> lst=this.infraccionService.getAll();
			if (lst.isEmpty()) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(lst);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@GetMapping("/{id}")
	public  ResponseEntity<ResponseREST> getInfraccion(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			InfraccionEntity infraccion=this.infraccionService.findById(id);
			if (infraccion==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(infraccion);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	

	
	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody InfraccionEntity infraccion, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			InfraccionEntity oInfraccion=infraccionService.save(infraccion);
			if (oInfraccion!=null) {
				return super.getCreatedRequest(oInfraccion);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody InfraccionEntity infraccion,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		infraccion.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			InfraccionEntity oInfraccion=infraccionService.save(infraccion);
			if (oInfraccion!=null) {
				return super.getOKRegistroRequest(oInfraccion);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseREST> eliminar(@PathVariable Long id) {
		if (id<=0) {
			return ResponseEntity.badRequest().build();
		}
		try {
			InfraccionEntity oInfraccion=infraccionService.delete(id);
			if (oInfraccion!=null) {
				return super.getOKRegistroRequest(oInfraccion);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
}
