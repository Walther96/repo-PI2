package com.proyecto.rutas.services.security.impl;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import com.proyecto.rutas.model.security.Usuario;
import com.proyecto.rutas.repository.security.UsuarioRepository;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	/*
	public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuarioEntity = usuarioRepository.loadUserByUsername(username);
		if (usuarioEntity == null) {
			throw new UsernameNotFoundException(username);
		}
		User user = new User(usuarioEntity.getUsuario(), usuarioEntity.getClave(), emptyList()/*Roles*/);

		return user;

	}

}
