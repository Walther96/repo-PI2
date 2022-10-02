package com.proyecto.rutas.controller.programacion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.proyecto.rutas.model.security.Usuario;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.DestinoService;
import com.proyecto.rutas.services.programacion.inf.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/usuario/v1")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class UsuarioController extends GenericController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping
	public  ResponseEntity<ResponseREST> getUsuarios() {
		try {
			List<Usuario> lst=this.usuarioService.getAll();
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
	public  ResponseEntity<ResponseREST> getUsuario(@PathVariable Long id) {
		try {
			if (id<=0) {
				return super.getBadIdRequest();
			}
			Usuario usuario=this.usuarioService.findById(id);
			if (usuario==null) {
				return super.getNotFoundRequest();
			}
			return super.getOKConsultaRequest(usuario);
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	@GetMapping("/findByUser/{user}")
	public  ResponseEntity<ResponseREST> getUsuario(@PathVariable String user) {
		if (user.isEmpty()) {
			return super.getBadIdRequest();
		}
		Usuario usuario=this.usuarioService.findByUsername(user);
		if (usuario==null) {
			return super.getNotFoundRequest();
		}
		return super.getOKConsultaRequest(usuario);
	}

	@PostMapping
	public ResponseEntity<ResponseREST> insertar( @Validated @RequestBody Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			usuario.setClave(passwordEncoder.encode(usuario.getClave()));

			Usuario oUsuario=usuarioService.save(usuario);
			if (oUsuario!=null) {
				return super.getCreatedRequest(oUsuario);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}
	
	@PutMapping	("/{id}")
	public ResponseEntity<ResponseREST> actualizar(@PathVariable Long id,@Validated @RequestBody Usuario usuario,
			BindingResult result) {
		if (id<=0) {
			return super.getBadIdRequest();
		}
		usuario.setId(id);
		
		if (result.hasErrors()) {
			return super.getBadRequest(result);
		}
		try {
			Usuario oUsuario=usuarioService.save(usuario);
			if (oUsuario!=null) {
				return super.getOKRegistroRequest(oUsuario);
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
			Usuario oUsuario=usuarioService.delete(id);
			if (oUsuario!=null) {
				return super.getOKRegistroRequest(oUsuario);
			}
			return super.getErrorRequest();
		} catch (ServiceException e) {
			log.error(e.getMessage());
			return super.getErrorRequest();
		}
	}

	
	
}
