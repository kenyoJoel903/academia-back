package com.kenyo.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.kenyo.academia.dao.IEstudianteDao;
import com.kenyo.academia.domain.Estudiante;
import com.kenyo.academia.service.IEstudianteService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstudianteServiceImpl implements IEstudianteService{
	
	@Autowired
	private IEstudianteDao dao;

	@Override
	public Mono<Estudiante> registrar(Estudiante t) {
		return dao.save(t);
	}

	@Override
	public Mono<Estudiante> modificar(Estudiante t) {
		return dao.save(t);
	}

	@Override
	public Flux<Estudiante> listar() {
		return dao.findAll();
	}

	@Override
	public Mono<Estudiante> listarPorId(String v) {
		return dao.findById(v);
	}

	@Override
	public Mono<Void> eliminar(String v) {
		return dao.deleteById(v);
	}

	@Override
	public Mono<Estudiante> buscarPorDni(String dni) {
		return dao.findOneByDni(dni);
	}

	@Override
	public Flux<Estudiante> findAllOrderByEdad() {
		return dao.findAll(Sort.by(new Order(Direction.DESC, "edad")));
	}
	
	

}
