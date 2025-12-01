package com.erp.service;

import com.erp.model.Produto;
import com.erp.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        // Lógica de negócio, como validação, pode ser adicionada aqui
        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public void excluir(Produto produto) {
        produtoRepository.delete(produto);
    }
}
