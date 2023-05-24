package com.estoquec.produtosapi.produto;

import java.math.BigDecimal;

public record DadosAtualizacaoProduto(Long id, String descricao, Long imei, BigDecimal valor, Categoria categoria, Modelo modelo, Memoria memoria) {
}
