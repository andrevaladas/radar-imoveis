/**
 * 
 */
package com.chronosystems.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.chronosystems.entity.enumeration.Estado;

/**
 * @author André Valadas
 */
@Entity
public class Cidade implements Serializable {

	private static final long serialVersionUID = 6446231364197185865L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CIDADE", unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 255)
	private String descricao;

	@Enumerated(EnumType.STRING)
	@Column(name = "CODIGO_ESTADO", nullable = false, length = 2)
	private Estado estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
}