package com.br.ProvaAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ProvaAPI.models.Colaborador;
import com.br.ProvaAPI.repository.ColaboradorRepository;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public ColaboradorServiceImpl(ColaboradorRepository colaboradorRepository) {
		super();
		this.colaboradorRepository = colaboradorRepository;
	}

	@Override
	public List<Colaborador> findAll() {
		return this.colaboradorRepository.findAll();
	}

	@Override
	public Colaborador find(Long id) {
		Colaborador  colaborador =  this.colaboradorRepository.findById(id).orElse(null);
		return colaborador;
	}

	@Override
	public Colaborador create(Colaborador colaborador) {
		this.colaboradorRepository.save(colaborador);
		return colaborador;
	}

	@Override
	public Colaborador update(Long id, Colaborador colaborador) {
		
		Optional<Colaborador> colaboradorExists = this.colaboradorRepository.findById(id);
		
		if(colaboradorExists != null) {
			colaborador.setId(colaboradorExists.get().getId());
			this.colaboradorRepository.save(colaborador);
			return colaborador;
		}
		
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Colaborador> colaborador = this.colaboradorRepository.findById(id);
		if(colaborador != null) this.colaboradorRepository.deleteById(id);
	}


}


























