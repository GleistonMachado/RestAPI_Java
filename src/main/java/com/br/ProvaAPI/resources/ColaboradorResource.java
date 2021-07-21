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

import com.br.ProvaAPI.models.Colaborador;
import com.br.ProvaAPI.services.ColaboradorService;

@RestController
@RequestMapping("/colaboradores")
public class ColaboradorResource {
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	public ColaboradorResource(ColaboradorService colaboradorService) {
		super();
		this.colaboradorService = colaboradorService;
	}

	@GetMapping(produces = "application/json")
	@ResponseBody
	public ResponseEntity<?> findAll() {
		List<Colaborador> list = new ArrayList<Colaborador>(this.colaboradorService.findAll());
		
		if(list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}
	
	@GetMapping(value = "/{id}")
	@ResponseBody
	public ResponseEntity<?> find(@PathVariable(value = "id") Long id) {
		Colaborador colaborador = this.colaboradorService.find(id);
		return new ResponseEntity<Colaborador>(colaborador, HttpStatus.OK);
	}
	
	@PostMapping
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Colaborador colaborador, Errors errors) {
		
		if(!errors.hasErrors()) {
			Colaborador colaboradorCreated = this.colaboradorService.create(colaborador);
			return new ResponseEntity<Colaborador>(colaboradorCreated, HttpStatus.CREATED);
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
	public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody Colaborador colaborador, Errors errors) {
		
		if(!errors.hasErrors()) {
			Colaborador colaboradorUpdadte = this.colaboradorService.update(id, colaborador);
			return new ResponseEntity<Colaborador>(colaboradorUpdadte, HttpStatus.OK);
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
		this.colaboradorService.delete(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}

}























