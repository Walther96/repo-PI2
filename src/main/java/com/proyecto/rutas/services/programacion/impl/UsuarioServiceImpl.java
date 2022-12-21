package com.proyecto.rutas.services.programacion.impl;

import static java.util.Collections.emptyList;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.Entity.DestinoEntity;
import com.proyecto.rutas.model.security.Usuario;
import com.proyecto.rutas.repository.security.UsuarioRepository;
import com.proyecto.rutas.services.Exception.ServiceException;
import com.proyecto.rutas.services.programacion.inf.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario findById(Long id) throws ServiceException {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id).orElse(null);
	}

	@Override
	public List<Usuario> getAll() throws ServiceException {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario save(Usuario usuario) throws ServiceException {
		// TODO Auto-generated method stub
		if (usuario.getId()>0) {
			Usuario oUsuario= this.findById(usuario.getId());
			BeanUtils.copyProperties(usuario, oUsuario);

		}
		
		return usuarioRepository.save(usuario);
	}

	@Override
	public Usuario delete(Long id) throws ServiceException {
		Usuario oUsuario= this.findById(id);
		oUsuario.setEstado("0");
		return usuarioRepository.save(oUsuario);
	}
	
	
	@Override
	public Usuario findByUsername(String username) throws UsernameNotFoundException {

		Usuario usuarioEntity = usuarioRepository.loadUserByUsername(username);
		if (usuarioEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		//Usuario userWithRol = usuarioRepository.findById(usuarioEntity.getId()).orElse(null);

		return usuarioEntity;

	}

	@Override
	public List<Usuario> findAllUsuarios() {
		return usuarioRepository.findAllUsuarios();
	}

}
