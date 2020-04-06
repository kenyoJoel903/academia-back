package com.kenyo.academia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kenyo.academia.dao.IRolDao;
import com.kenyo.academia.domain.Rol;
import com.kenyo.academia.service.IRolService;

import reactor.core.publisher.Flux;

@Service
public class IRolServiceImpl implements IRolService{
	
	@Autowired
	private IRolDao dao;

	@Override
	public Flux<Rol> listar() {
		return dao.findAll();
	}

}
