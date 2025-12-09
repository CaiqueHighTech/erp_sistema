package com.erp.service;

import com.erp.model.Venda;
import com.erp.model.Produto;
import com.erp.model.Estoque;
import com.erp.repository.VendaRepository;
import com.erp.repository.ProdutoRepository;
import com.erp.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Venda salvar(Venda venda) {
        return vendaRepository.save(venda);
    }

    public List<Venda> listarTodos() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id);
    }

    public void excluir(Venda venda) {
        vendaRepository.delete(venda);
    }
    
    /**
     * Registra uma venda validando o estoque disponível e atualizando as quantidades
     * @param venda - Venda a ser registrada
     * @return Venda registrada ou null se não houver estoque suficiente
     */
    public Venda registrarVenda(Venda venda) {
        if (venda.getProduto() == null || venda.getQuantidadeVendida() == null || venda.getQuantidadeVendida() <= 0) {
            throw new IllegalArgumentException("Produto e quantidade válidos são obrigatórios");
        }
        
        // Busca o estoque do produto
        List<Estoque> estoques = estoqueRepository.findAll();
        Estoque estoque = null;
        for (Estoque e : estoques) {
            if (e.getProduto().getId().equals(venda.getProduto().getId())) {
                estoque = e;
                break;
            }
        }
        
        if (estoque == null) {
            throw new IllegalArgumentException("Produto não encontrado no estoque");
        }
        
        // Valida se há quantidade suficiente
        if (estoque.getQuantidade() < venda.getQuantidadeVendida()) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente. Disponível: " + estoque.getQuantidade());
        }
        
        // Atualiza o estoque
        estoque.setQuantidade(estoque.getQuantidade() - venda.getQuantidadeVendida());
        estoqueRepository.save(estoque);
        
        // Atualiza a quantidade em estoque do produto
        Produto produto = venda.getProduto();
        produto.setQuantidadeEstoque(estoque.getQuantidade());
        produtoRepository.save(produto);
        
        // Salva a venda
        return vendaRepository.save(venda);
    }
}
