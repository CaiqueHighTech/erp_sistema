package com.erp.controller;

import com.erp.model.Venda;
import com.erp.model.Produto;
import com.erp.service.VendaService;
import com.erp.service.ProdutoService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class VendaController extends BaseAction {
    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obter o ApplicationContext do Spring
            WebApplicationContext applicationContext = WebApplicationContextUtils
                    .getWebApplicationContext(getServlet().getServletContext());
            
            VendaService vendaService = (VendaService) applicationContext.getBean("vendaService");
            ProdutoService produtoService = (ProdutoService) applicationContext.getBean("produtoService");
            
            String action = request.getParameter("action");
            
            if ("salvar".equals(action)) {
                // Obtém os dados do formulário
                String produtoIdStr = request.getParameter("produtoId");
                String valorUnitarioStr = request.getParameter("valorUnitario");
                String quantidadeStr = request.getParameter("quantidade");
                
                try {
                    Long produtoId = Long.parseLong(produtoIdStr);
                    Double valorUnitario = Double.parseDouble(valorUnitarioStr);
                    Integer quantidade = Integer.parseInt(quantidadeStr);
                    
                    // Busca o produto
                    Produto produto = produtoService.buscarPorId(produtoId);
                    if (produto == null) {
                        request.setAttribute("erro", "Produto não encontrado");
                    } else {
                        // Cria a venda
                        Venda venda = new Venda();
                        venda.setProduto(produto);
                        venda.setValorUnitario(valorUnitario);
                        venda.setQuantidadeVendida(quantidade);
                        venda.setValorTotal(valorUnitario * quantidade);
                        
                        // Registra a venda (validando estoque)
                        vendaService.registrarVenda(venda);
                        request.setAttribute("sucesso", "Venda registrada com sucesso!");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("erro", "Erro ao converter valores. Verifique os dados inseridos.");
                } catch (IllegalArgumentException e) {
                    request.setAttribute("erro", e.getMessage());
                } catch (Exception e) {
                    request.setAttribute("erro", "Erro ao registrar venda: " + e.getMessage());
                }
            }
            
            // Lista todas as vendas
            List<Venda> vendas = vendaService.listarTodos();
            request.setAttribute("vendas", vendas);
            
            // Lista todos os produtos (para o formulário)
            List<Produto> produtos = produtoService.listarTodos();
            request.setAttribute("produtos", produtos);
            
            return mapping.findForward("listarVendas");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Erro ao processar solicitação: " + e.getMessage());
            return mapping.findForward("erro");
        }
    }
}
