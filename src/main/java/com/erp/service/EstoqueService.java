package com.erp.service;

import com.erp.model.Estoque;
import com.erp.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    public Estoque salvar(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorId(Long id) {
        return estoqueRepository.findById(id);
    }

    public void excluir(Estoque estoque) {
        estoqueRepository.delete(estoque);
    }
}
