package com.residencia.comercio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.comercio.dtos.ProdutoDTO;
import com.residencia.comercio.entities.Produto;
import com.residencia.comercio.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepository produtoRepository;
	
	public List<Produto> findAllProduto(){
		return produtoRepository.findAll();
	}
	
	public Produto findProdutoById(Integer id) {
		return produtoRepository.findById(id).isPresent() ?
				produtoRepository.findById(id).get() : null;
	}

	public ProdutoDTO findProdutoDTOById(Integer id) {
		Produto produto = produtoRepository.findById(id).isPresent() ?
				produtoRepository.findById(id).get() : null;
		
		ProdutoDTO produtoDTO = new ProdutoDTO();
		if(null != produto) {
			produtoDTO = converterEntidadeParaDto(produto);
		}
		return produtoDTO;
	}
	
	public Produto saveProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public ProdutoDTO saveProdutoDTO(ProdutoDTO produtoDTO) {
			
		Produto produto = new Produto();
		
		produto.setIdProduto(produtoDTO.getIdProduto());
		Produto novoProduto = produtoRepository.save(produto);
		
		return converterEntidadeParaDto(novoProduto);
	}
	
	public Produto updateProduto(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto updateById(Produto produto, Integer id) {

        Produto produtoBD = produtoRepository.findById(id).isPresent() ?
                produtoRepository.findById(id).get() : null;

        Produto produtoAtualizado = null;
        if(null != produtoBD) {
            produtoBD.setCategoria(produto.getCategoria());
            produtoBD.setNomeProduto(produto.getNomeProduto());
            produtoBD.setSku(produto.getSku());
            produtoBD.setFornecedor(produto.getFornecedor());
            produtoBD.setCategoria(produto.getCategoria());
            produtoAtualizado = produtoRepository.save(produtoBD);
        }

        return produtoRepository.save(produtoAtualizado);
    }
	
	public void deleteProdutoById(Integer id) {
		Produto prod = produtoRepository.findById(id).get();
		produtoRepository.delete(prod);
	}
	
	public void deleteProduto(Produto produto) {
		produtoRepository.delete(produto);
	}
	
	private Produto convertDTOToEntidade(ProdutoDTO produtoDTO){
		Produto produto = new Produto();
		
		produto.setIdProduto(produtoDTO.getIdProduto());
		return produto;
	}
		
	private ProdutoDTO converterEntidadeParaDto(Produto produto) {
		ProdutoDTO produtoDTO = new ProdutoDTO();
		produtoDTO.setIdProduto(produto.getIdProduto());
		return produtoDTO;
	}
}
