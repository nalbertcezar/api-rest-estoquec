package com.estoquec.produtosapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoquec.produtosapi.produto.DadosAtualizacaoProduto;
import com.estoquec.produtosapi.produto.DadosCadastroProduto;
import com.estoquec.produtosapi.produto.DadosListagemProduto;
import com.estoquec.produtosapi.produto.Produto;
import com.estoquec.produtosapi.produto.ProdutoRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroProduto dados) {
		repository.save(new Produto(dados));
	}
	
	@GetMapping
	public List<DadosListagemProduto> listar() {
		return repository.findAll().stream().map(DadosListagemProduto::new).toList();
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody DadosAtualizacaoProduto dados) {
		var produto = repository.getReferenceById(dados.id());
		produto.atualizarDados(dados);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
