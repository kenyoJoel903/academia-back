package com.kenyo.academia.service;


import com.kenyo.academia.domain.Rol;

import reactor.core.publisher.Flux;

public interface IRolService {
	
	Flux<Rol> listar();

}
