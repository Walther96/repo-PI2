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
import com.proyecto.rutas.model.Entity.RutaEntity;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.RutaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/ruta/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class RutaController extends GenericController {


	@Autowired
	private RutaService rutaService;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getRutas() {
		try {
			List<RutaEntity> lst=this.rutaService.getAll();
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
	public  ResponseEntity<ResponseREST> getRuta(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			RutaEntity ruta=this.rutaService.findById(id);
			if (ruta==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(ruta);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	@GetMapping("/nombreruta/{nombre}")
	public  ResponseEntity<ResponseREST> getRutaXNombre(@PathVariable String nombre) {
		if (nombre==null) {
			return super.getBadIdRequest();
		}
		RutaEntity ruta=this.rutaService.FindRutaXNombre(nombre);
		if (ruta==null) {
			return super.getNotFoundRequest();
		}
		return super.getOKConsultaRequest(ruta);
	}
	
	

	
	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody RutaEntity ruta, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			RutaEntity oRuta=rutaService.save(ruta);
			if (oRuta!=null) {
				return super.getCreatedRequest(oRuta);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody RutaEntity ruta,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		ruta.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			RutaEntity oRuta=rutaService.save(ruta);
			if (oRuta!=null) {
				return super.getOKRegistroRequest(oRuta);
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
			RutaEntity oRuta=rutaService.delete(id);
			if (oRuta!=null) {
				return super.getOKRegistroRequest(oRuta);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
}
