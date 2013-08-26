/**
 * 
 */
package com.chronosystems.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author André Valadas
 */
@Entity
public class Bairro implements Serializable {

	private static final long serialVersionUID = 5594258333880522494L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_BAIRRO", unique = true, nullable = false)
	private Long id;

	@Column(nullable = false, length = 255)
	private String descricao;

	@ManyToOne(fetch = FetchType.EAGER, cascade = javax.persistence.CascadeType.ALL)
	@JoinColumn(name = "ID_CIDADE", nullable = false)
	private Cidade cidade;

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

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
}