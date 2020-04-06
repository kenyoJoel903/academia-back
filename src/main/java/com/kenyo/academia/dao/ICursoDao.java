package com.kenyo.academia.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.kenyo.academia.domain.Curso;

public interface ICursoDao extends ReactiveMongoRepository<Curso, String> {

}
