package com.br.ProvaAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.ProvaAPI.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {


}
