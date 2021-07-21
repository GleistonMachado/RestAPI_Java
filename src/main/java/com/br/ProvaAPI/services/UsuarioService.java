package com.br.ProvaAPI.services;

import java.util.List;

import com.br.ProvaAPI.models.Usuario;

public interface UsuarioService {

	public List<Usuario> findAll();
	public Usuario find(Long id);
	public Usuario create(Usuario product);
	public Usuario update(Long id, Usuario product);
	public void delete(Long id);
}
