package com.kenyo.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenyo.academia.dao.ICursoDao;
import com.kenyo.academia.domain.Curso;
import com.kenyo.academia.service.ICursoService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CursoServiceImpl implements ICursoService{

	@Autowired
	private ICursoDao dao;

	@Override
	public Mono<Curso> registrar(Curso t) {
		return dao.save(t);
	}

	@Override
	public Mono<Curso> modificar(Curso t) {
		return dao.save(t);
	}

	@Override
	public Flux<Curso> listar() {
		return dao.findAll();
	}

	@Override
	public Mono<Curso> listarPorId(String v) {
		return dao.findById(v);
	}

	@Override
	public Mono<Void> eliminar(String v) {
		return dao.deleteById(v);
	}

}
