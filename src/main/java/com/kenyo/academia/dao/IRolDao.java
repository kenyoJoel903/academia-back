package com.kenyo.academia.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.kenyo.academia.domain.Rol;

public interface IRolDao extends ReactiveMongoRepository<Rol, String>{

}
