package com.kenyo.academia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IController<T> {
	
	Mono<ResponseEntity<Flux<T>>> listar();
	
	Mono<ResponseEntity<T>> listarPorId(String id);
	
	Mono<ResponseEntity<T>> registrar(T t, final ServerHttpRequest req);
	
	Mono<ResponseEntity<T>> modificar(T t);
	
	Mono<ResponseEntity<Void>> eliminar(String id);

}
