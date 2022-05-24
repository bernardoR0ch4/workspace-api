package com.residencia.firstapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.firstapi.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	Autor findByAutorNomeIgnoreCase(String nome); //ignore diz que é para encontrar independente de ser letra maiuscula ou não
    //Autor findByAutorNome(String nome);
	
}