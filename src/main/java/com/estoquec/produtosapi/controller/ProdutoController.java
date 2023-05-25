package com.estoquec.produtosapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.estoquec.produtosapi.produto.DadosAtualizacaoProduto;
import com.estoquec.produtosapi.produto.DadosCadastroProduto;
import com.estoquec.produtosapi.produto.DadosDetalhamentoProduto;
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
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
		var produto = new Produto(dados);
		repository.save(produto);
		var uri = uriBuilder.path("/produtos/{id}").buildAndExpand(produto.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoProduto(produto));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemProduto>> listar(@PageableDefault(size = 10, sort = {"descricao"}) Pageable pageable) {
		var page = repository.findAll(pageable).map(DadosListagemProduto::new);
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity listarDetalhesProduto(@PathVariable Long id) {
		var produto = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody DadosAtualizacaoProduto dados) {
		var produto = repository.getReferenceById(dados.id());
		produto.atualizarDados(dados);
		return ResponseEntity.ok(new DadosDetalhamentoProduto(produto));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deletar(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
