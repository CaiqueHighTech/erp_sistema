package com.erp.controller;

import com.erp.model.Produto;
import com.erp.service.ProdutoService;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Controller (Action) do Struts para gerenciar operações de Produto.
 */
public class ProdutoController extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Obtém o serviço do Spring Context
        ProdutoService produtoService = (ProdutoService) getBean(getServlet().getServletContext(), "produtoService");

        String action = request.getParameter("action");

        if ("listar".equals(action) || action == null) {
            List<Produto> produtos = produtoService.listarTodos();
            request.setAttribute("produtos", produtos);
            return mapping.findForward("listarProdutos");
        } else if ("salvar".equals(action)) {
            // Aqui, o ActionForm seria usado para obter os dados do formulário
            // Para simplificar, vamos criar um produto de exemplo
            Produto novoProduto = new Produto();
            novoProduto.setNome("Produto Teste");
            novoProduto.setPreco(100.00);
            novoProduto.setQuantidadeEstoque(50);
            produtoService.salvar(novoProduto);

            // Após salvar, redireciona para a listagem
            return mapping.findForward("listarProdutosRedirect");
        }

        return mapping.findForward("erro");
    }
}
