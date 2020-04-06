package com.kenyo.academia.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.kenyo.academia.domain.Matricula;

public interface IMatriculaDao extends ReactiveMongoRepository<Matricula, String> {

}
