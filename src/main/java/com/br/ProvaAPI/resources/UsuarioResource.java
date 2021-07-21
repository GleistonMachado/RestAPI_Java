package com.br.ProvaAPI.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.br.ProvaAPI.models.Usuario;
import com.br.ProvaAPI.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioResource(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Usuario> list = new ArrayList<Usuario>(this.usuarioService.findAll());
		
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
		Usuario usuario = this.usuarioService.find(id);
		
		if(usuario == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);			
		}
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, Errors errors) {
		
		if(!errors.hasErrors()) {
			Usuario usuarioCreated = this.usuarioService.create(usuario);
			return new ResponseEntity<Usuario>(usuarioCreated, HttpStatus.CREATED);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(","))
						);
	}
	
	@PutMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Usuario usuario, Errors errors) {
		
		if(!errors.hasErrors()) {
			Usuario usuarioUpdadte = this.usuarioService.update(id, usuario);
			return new ResponseEntity<Usuario>(usuarioUpdadte, HttpStatus.OK);
		}
		
		return ResponseEntity
				.badRequest()
				.body(errors.getAllErrors()
						.stream()
						.map(msg -> msg.getDefaultMessage())
						.collect(Collectors.joining(","))
						); 
	}
	
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") Long id, HttpServletResponse response) {
		this.usuarioService.delete(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}























