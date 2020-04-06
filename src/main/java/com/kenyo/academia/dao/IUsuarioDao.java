package com.kenyo.academia.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.kenyo.academia.domain.Usuario;

import reactor.core.publisher.Mono;

public interface IUsuarioDao extends ReactiveMongoRepository<Usuario, String> {

	Mono<Usuario> findOneByUsuario(String usuario);
}
