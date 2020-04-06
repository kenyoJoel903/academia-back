package com.kenyo.academia.dao;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.kenyo.academia.domain.Estudiante;

import reactor.core.publisher.Mono;

public interface IEstudianteDao extends ReactiveMongoRepository<Estudiante, String> {

	Mono<Estudiante> findOneByDni(String dni);

}
