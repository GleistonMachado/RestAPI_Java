package com.br.ProvaAPI.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.ProvaAPI.models.Usuario;
import com.br.ProvaAPI.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository productRepository;
	
	public UsuarioServiceImpl(UsuarioRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Usuario> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public Usuario find(Long id) {
		Usuario  product =  this.productRepository.findById(id).orElse(null);
		return product;
	}

	@Override
	public Usuario create(Usuario product) {
		this.productRepository.save(product);
		return product;
	}

	@Override
	public Usuario update(Long id, Usuario product) {
		
		Optional<Usuario> productExists = this.productRepository.findById(id);
		
		if(productExists != null) {
			product.setId(productExists.get().getId());
			this.productRepository.save(product);
			return product;
		}
		
		return null;
	}

	@Override
	public void delete(Long id) {
		Optional<Usuario> product = this.productRepository.findById(id);
		if(product != null) this.productRepository.deleteById(id);
	}

}


























