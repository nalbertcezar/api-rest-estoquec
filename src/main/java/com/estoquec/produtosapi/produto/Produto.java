package com.estoquec.produtosapi.produto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "produtos")
@Table(name = "produtos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private Long imei;
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@Enumerated(EnumType.STRING)
	private Modelo modelo;
	@Enumerated(EnumType.STRING)
	private Memoria memoria;
	
	public Produto(DadosCadastroProduto dados) {
		this.descricao = dados.descricao();
		this.imei = dados.imei();
		this.valor = dados.valor();
		this.categoria = dados.categoria();
		this.modelo = dados.modelo();
		this.memoria = dados.memoria();
	}
		
}
