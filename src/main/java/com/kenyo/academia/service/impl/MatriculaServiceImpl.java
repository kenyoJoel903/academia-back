package com.kenyo.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenyo.academia.dao.IMatriculaDao;
import com.kenyo.academia.domain.Matricula;
import com.kenyo.academia.service.IMatriculaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MatriculaServiceImpl implements IMatriculaService{

	@Autowired
	private IMatriculaDao dao;

	@Override
	public Mono<Matricula> registrar(Matricula t) {
		return dao.save(t);
	}

	@Override
	public Mono<Matricula> modificar(Matricula t) {
		return dao.save(t);
	}

	@Override
	public Flux<Matricula> listar() {
		return dao.findAll();
	}

	@Override
	public Mono<Matricula> listarPorId(String v) {
		return dao.findById(v);
	}

	@Override
	public Mono<Void> eliminar(String v) {
		return dao.deleteById(v);
	}
}
