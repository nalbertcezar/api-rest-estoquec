package com.estoquec.produtosapi.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(String descricao,
		@NotNull
		Long imei,
		@NotNull
		BigDecimal valor,
		@NotNull
		Categoria categoria,
		@NotNull
		Modelo modelo,
		Memoria memoria) {
}
