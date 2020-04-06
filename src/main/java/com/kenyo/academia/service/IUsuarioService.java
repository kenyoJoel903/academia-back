package com.kenyo.academia.service;



import com.kenyo.academia.domain.Usuario;
import com.kenyo.academia.security.User;

import reactor.core.publisher.Mono;

public interface IUsuarioService extends Icrud<Usuario, String>{
	
	Mono<User> buscarPorUsuario(String usuario);

}
