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
import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.DestinoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/destino/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class DestinoController extends GenericController {
	
	@Autowired
	private DestinoService destinoService;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getDestinos() {
		try {
			List<DestinoEntity> lst=this.destinoService.getAll();
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
	public  ResponseEntity<ResponseREST> getDestino(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			DestinoEntity destino=this.destinoService.findById(id);
			if (destino==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(destino);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	

	
	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody DestinoEntity destino, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			DestinoEntity oDestino=destinoService.save(destino);
			if (oDestino!=null) {
				return super.getCreatedRequest(oDestino);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody DestinoEntity destino,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		destino.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			DestinoEntity oDestino=destinoService.save(destino);
			if (oDestino!=null) {
				return super.getOKRegistroRequest(oDestino);
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
			DestinoEntity oDestino=destinoService.delete(id);
			if (oDestino!=null) {
				return super.getOKRegistroRequest(oDestino);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
}
