package com.erp.repository;

import com.erp.model.Venda;
import org.springframework.stereotype.Repository;

/**
 * Repositório específico para a entidade Venda.
 * Estende GenericDAO para herdar as operações CRUD básicas do Hibernate.
 * A anotação @Repository é do Spring e será usada para injeção de dependência.
 */
@Repository
public class VendaRepository extends GenericDAO<Venda, Long> {

    // Métodos específicos para Venda podem ser adicionados aqui, se necessário.
}
