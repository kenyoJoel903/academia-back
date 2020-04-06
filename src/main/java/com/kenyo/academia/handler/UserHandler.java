package com.kenyo.academia.handler;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kenyo.academia.domain.Estudiante;
import com.kenyo.academia.domain.Usuario;
import com.kenyo.academia.service.IRolService;
import com.kenyo.academia.service.IUsuarioService;
import com.kenyo.academia.validator.RequestValidator;

import reactor.core.publisher.Mono;

@Component
public class UserHandler {
	
	@Autowired
	private IUsuarioService service;
	
	@Autowired
	private IRolService rolService;
	
	@Autowired
	private RequestValidator validadorGeneral;
	
	public Mono<ServerResponse> listarRoles(ServerRequest req){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(rolService.listar(), Estudiante.class);
	}
	
	public Mono<ServerResponse> listarUsuarios(ServerRequest req){
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_STREAM_JSON)
				.body(service.listar(), Estudiante.class);
	}
	
	public Mono<ServerResponse> registrar(ServerRequest req){
		Mono<Usuario> usuarioMono = req.bodyToMono(Usuario.class);
		return usuarioMono
					.flatMap(this.validadorGeneral::validar)
					.flatMap(service::registrar)
					.flatMap(e -> {
						//e.setClave(clave);
						return ServerResponse.created(URI.create(req.uri().toString().concat("/").concat(e.getId())))
						.contentType(MediaType.APPLICATION_STREAM_JSON)
						.body(fromValue(e));
					});
	}; 
	
	

}
