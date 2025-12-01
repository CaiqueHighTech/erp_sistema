package com.erp.service;

import com.erp.model.Venda;
import com.erp.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

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
}
