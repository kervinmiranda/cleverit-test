package com.cleverit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {

	@Id
	private String id;
	@Column(name = "patente", length = 50)
	private String patente;
	@Column(name = "tipoauto", length = 50)
	private String tipoAuto;
	@Column(name = "color", length = 50)
	private String color;

	public Car() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

	public String getTipoAuto() {
		return tipoAuto;
	}

	public void setTipoAuto(String tipoAuto) {
		this.tipoAuto = tipoAuto;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Auto {id=" + id + ", patente=" + patente + ", tipoAuto=" + tipoAuto + ", color=" + color + "}";
	}

}
