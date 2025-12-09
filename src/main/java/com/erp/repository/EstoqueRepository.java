package com.erp.repository;

import com.erp.model.Estoque;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositório específico para a entidade Estoque.
 * Estende GenericDAO para herdar as operações CRUD básicas do Hibernate.
 * A anotação @Repository é do Spring e será usada para injeção de dependência.
 */
@Repository
public class EstoqueRepository extends GenericDAO<Estoque, Long> {

    // Métodos específicos para Estoque podem ser adicionados aqui, se necessário.
    
    public Estoque findByProdutoId(Long produtoId) {
        List<Estoque> estoques = findAll();
        for (Estoque estoque : estoques) {
            if (estoque.getProduto() != null && estoque.getProduto().getId().equals(produtoId)) {
                return estoque;
            }
        }
        return null;
    }
}

