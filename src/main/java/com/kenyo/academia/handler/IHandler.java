package com.kenyo.academia.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface IHandler {
	
	Mono<ServerResponse> listar(ServerRequest req);
	Mono<ServerResponse> listarPorId(ServerRequest req);
	Mono<ServerResponse> registrar(ServerRequest req);
	Mono<ServerResponse> modificar(ServerRequest req);
	Mono<ServerResponse> eliminar(ServerRequest req);

}
