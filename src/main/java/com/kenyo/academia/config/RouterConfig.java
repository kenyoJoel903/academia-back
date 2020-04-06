package com.kenyo.academia.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kenyo.academia.handler.CursoHandler;
import com.kenyo.academia.handler.EstudianteHandler;
import com.kenyo.academia.handler.MatriculaHandler;
import com.kenyo.academia.handler.UserHandler;

@Configuration
public class RouterConfig {
	
	@Bean
	public RouterFunction<ServerResponse> rutasEstudiantes(EstudianteHandler handler){
		return route(GET("/v1/estudiantes"), handler::listar)
				.andRoute(GET("/v1/estudiantes/{id}"), handler::listarPorId)
				.andRoute(POST("/v1/estudiantes"), handler::registrar)
				.andRoute(PUT("/v1/estudiantes"), handler::modificar)
				.andRoute(DELETE("/v1/estudiantes/{id}"), handler::eliminar)
				.andRoute(GET("/v1/estudiantes/orderByEdad/simple"), handler::listarPorEdadDesc)
				.andRoute(GET("/v1/estudiantes/orderByEdad/paralelo"), handler::listarParaleloPorEdadDesc);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasCursos(CursoHandler handler){
		return route(GET("/v1/cursos"), handler::listar)
				.andRoute(GET("/v1/cursos/{id}"), handler::listarPorId)
				.andRoute(POST("/v1/cursos"), handler::registrar)
				.andRoute(PUT("/v1/cursos"), handler::modificar)
				.andRoute(DELETE("/v1/cursos/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasMatriculas(MatriculaHandler handler){
		return route(GET("/v1/matriculas"), handler::listar)
				.andRoute(GET("/v1/matriculas/{id}"), handler::listarPorId)
				.andRoute(POST("/v1/matriculas"), handler::registrar)
				.andRoute(PUT("/v1/matriculas"), handler::modificar)
				.andRoute(DELETE("/v1/matriculas/{id}"), handler::eliminar);
	}
	
	@Bean
	public RouterFunction<ServerResponse> rutasUsuarios(UserHandler handler){
		return route(GET("/v1/usuarios/roles"), handler::listarRoles)
				.andRoute(GET("/v1/usuarios"), handler::listarUsuarios)
				.andRoute(POST("/v1/usuarios"), handler::registrar);
	}

}
