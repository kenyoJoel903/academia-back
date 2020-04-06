package com.kenyo.academia.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Estudiante {

	@Id
	private String id;
	
	@NotEmpty
	private String nombres;
	@NotEmpty
	private String apellidos;
	@NotEmpty
	@Size(max = 8)
	private String dni;
	@NotNull
	@Max(100)
	@Min(0)
	private double edad;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getEdad() {
		return edad;
	}

	public void setEdad(double edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Estudiante [id=" + id + ", nombres=" + nombres + ", apellidos=" + apellidos + ", dni=" + dni + ", edad="
				+ edad + "]";
	}
	
	
	
	
}
