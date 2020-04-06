package com.kenyo.academia.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kenyo.academia.domain.Estudiante;
import com.kenyo.academia.service.IEstudianteService;
import com.kenyo.academia.validator.RequestValidator;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

@Component
public class EstudianteHandler implements IHandler {
	
	@Autowired
	private IEstudianteService service;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	public Mono<ServerResponse> listar(ServerRequest req){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(service.listar(), Estudiante.class);
	}
	
	public Mono<ServerResponse> listarPorId(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
				.flatMap(e -> ServerResponse.ok()
						.contentType(MediaType.APPLICATION_STREAM_JSON)
						.body(fromValue(e))
				)
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		Mono<Estudiante> estudianteMono = req.bodyToMono(Estudiante.class);
		return estudianteMono
					.flatMap(this.validadorGeneral::validar)
					.flatMap(service::registrar)
					.flatMap(e -> ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
							.contentType(MediaType.APPLICATION_STREAM_JSON)
							.body(fromValue(e)));
	}
	
	public Mono<ServerResponse> modificar(ServerRequest req){
		Mono<Estudiante> estudianteMono = req.bodyToMono(Estudiante.class);
		return estudianteMono
					.flatMap(this.validadorGeneral::validar)
					.flatMap(service::modificar)
					.flatMap(e -> ServerResponse.ok()
							.contentType(MediaType.APPLICATION_STREAM_JSON)
							.body(fromValue(e))
					).switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> eliminar(ServerRequest req){
		String id = req.pathVariable("id");
		return service.listarPorId(id)
					.flatMap(p -> service.eliminar(p.getId())
							.then(ServerResponse.noContent().build())
					).switchIfEmpty(ServerResponse.notFound().build());
	}
	
	public Mono<ServerResponse> listarPorEdadDesc(ServerRequest req){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(service.findAllOrderByEdad(), Estudiante.class);
	}
	
	public Mono<ServerResponse> listarParaleloPorEdadDesc(ServerRequest req){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(service.findAllOrderByEdad().parallel().runOn(Schedulers.elastic()), Estudiante.class);
	}

}
