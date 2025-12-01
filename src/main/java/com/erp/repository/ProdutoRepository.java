package com.erp.repository;

import com.erp.model.Produto;
import org.springframework.stereotype.Repository;

/**
 * Repositório específico para a entidade Produto.
 * Estende GenericDAO para herdar as operações CRUD básicas do Hibernate.
 * A anotação @Repository é do Spring e será usada para injeção de dependência.
 */
@Repository
public class ProdutoRepository extends GenericDAO<Produto, Long> {

    // Métodos específicos para Produto podem ser adicionados aqui, se necessário.
}
