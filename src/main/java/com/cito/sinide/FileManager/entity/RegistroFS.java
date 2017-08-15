package com.cito.sinide.FileManager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="RegistroFS")
public class RegistroFS{

	@Id
	@GenericGenerator(name = "generator", strategy = "guid", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "Id" , columnDefinition="uniqueidentifier")
	private String id;
	
	@Column(name="Nombre", length=200)
	private String nombre;

	@Lob
	@Column(name="Archivo", length=2147483647)
	private byte[] archivo;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public byte[] getArchivo() {
		return archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
}
