package com.estoquec.produtosapi.produto;

import java.math.BigDecimal;

public record DadosDetalhamentoProduto(Long id, String descricao, Long imei, BigDecimal valor, Categoria categoria, Modelo modelo, Memoria memoria) {
	
	public DadosDetalhamentoProduto(Produto produto) {
		this(produto.getId(), produto.getDescricao(), produto.getImei(), produto.getValor(), produto.getCategoria(), produto.getModelo(), produto.getMemoria());
	}
}
