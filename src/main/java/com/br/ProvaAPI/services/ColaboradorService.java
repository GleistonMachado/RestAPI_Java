package com.br.ProvaAPI.services;

import java.util.List;

import com.br.ProvaAPI.models.Colaborador;

public interface ColaboradorService {

	public List<Colaborador> findAll();
	public Colaborador find(Long id);
	public Colaborador create(Colaborador colaborador);
	public Colaborador update(Long id, Colaborador colaborador);
	public void delete(Long id);
}
