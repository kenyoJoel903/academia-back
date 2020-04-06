package com.kenyo.academia.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kenyo.academia.domain.Matricula;
import com.kenyo.academia.service.IMatriculaService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v2/matriculas")
public class MatriculaController implements IController<Matricula> {
	
	@Autowired
	private IMatriculaService service;
	
	@GetMapping
	@Override
	public Mono<ResponseEntity<Flux<Matricula>>> listar() {
		return Mono.just(ResponseEntity.ok()
							.contentType(MediaType.APPLICATION_STREAM_JSON)
							.body(service.listar()));
	}

	@GetMapping("/{id}")
	@Override
	public Mono<ResponseEntity<Matricula>> listarPorId(@PathVariable("id")  String id) {
		return service.listarPorId(id)
				.map(p -> ResponseEntity.ok()
						.contentType(MediaType.APPLICATION_STREAM_JSON)
						.body(p))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PostMapping
	@Override
	public Mono<ResponseEntity<Matricula>> registrar(@Valid @RequestBody Matricula t, ServerHttpRequest req) {
		return service.registrar(t)
					.map(p -> ResponseEntity.created(URI.create(req.getURI().toString().concat("/").concat(p.getId())))
							.contentType(MediaType.APPLICATION_STREAM_JSON)
							.body(p));
	}

	@PutMapping
	@Override
	public Mono<ResponseEntity<Matricula>> modificar(@Valid @RequestBody Matricula t) {
		return service.modificar(t)
					.map(p -> ResponseEntity.ok()
								.contentType(MediaType.APPLICATION_STREAM_JSON)
								.body(p))
					.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	@Override
	public Mono<ResponseEntity<Void>> eliminar(String id) {
		return service.listarPorId(id)
					.flatMap(p -> {
						return service.eliminar(p.getId())
									.then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
					})
					.defaultIfEmpty(ResponseEntity.notFound().build());
	}

}
