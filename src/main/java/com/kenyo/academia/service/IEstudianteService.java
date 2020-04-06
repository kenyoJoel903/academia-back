package com.kenyo.academia.service;


import com.kenyo.academia.domain.Estudiante;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEstudianteService extends Icrud<Estudiante, String>{
	
	Mono<Estudiante> buscarPorDni(String dni);
	
	Flux<Estudiante> findAllOrderByEdad();

}
